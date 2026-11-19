package com.example.drivecost.data.repository

import com.example.drivecost.data.remote.service.FuelService
import com.example.drivecost.model.State
import com.example.drivecost.repository.FuelRepository

class FuelRepositoryImplement(private val fuelService: FuelService): FuelRepository {

    override suspend fun getStates(): List<State> {
        TODO("Not yet implemented")
    }

    override suspend fun getGasolinePrice(uf: String): Double? {
        TODO("Not yet implemented")
    }

    override suspend fun getDieselPrice(uf: String): Double? {
        TODO("Not yet implemented")
    }

    override suspend fun getNationalGasolinePrice(): Double? {
        TODO("Not yet implemented")
    }

}