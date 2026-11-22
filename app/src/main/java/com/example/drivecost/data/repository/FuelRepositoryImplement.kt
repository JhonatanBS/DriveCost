package com.example.drivecost.data.repository

import com.example.drivecost.data.local.brazilianStates
import com.example.drivecost.data.remote.service.FuelService
import com.example.drivecost.model.StateFuelInformation
import com.example.drivecost.repository.FuelRepository

class FuelRepositoryImplement(private val fuelService: FuelService) : FuelRepository {

    override suspend fun getStatesWithFuelInfo(): List<StateFuelInformation> {
        val response = fuelService.getFuelBrazil()

        return brazilianStates.map { state ->
            val uf = state.abbreviation.lowercase()

            StateFuelInformation(
                flagUrl = response.icones[uf].orEmpty(),
                state = state.name,
                uf = state.abbreviation,
                gasoline = response.precos.gasolina[uf]
                    ?.replace(",", ".")
                    ?.toDoubleOrNull(),
                diesel = response.precos.diesel[uf]
                    ?.replace(",", ".")
                    ?.toDoubleOrNull()
            )

        }
    }

    override suspend fun getGasolinePrice(uf: String): Double? {
        val response = fuelService.getFuelBrazil()

        val normalizeUf = uf.trim().lowercase()

        return response.precos.gasolina[normalizeUf]
            ?.replace(",", ".")
            ?.toDoubleOrNull()
    }

    override suspend fun getDieselPrice(uf: String): Double? {
        TODO("Not yet implemented")
    }

    override suspend fun getNationalGasolinePrice(): Double? {
        val response = fuelService.getFuelBrazil()

        return response.precos.gasolina["br"]
            ?.replace(",", ".")
            ?.toDoubleOrNull()
    }
}