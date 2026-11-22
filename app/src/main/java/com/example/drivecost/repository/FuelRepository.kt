package com.example.drivecost.repository

import com.example.drivecost.model.StateFuelInformation

interface FuelRepository {

    suspend fun getStatesWithFuelInfo(): List<StateFuelInformation>

    suspend fun getGasolinePrice(uf: String): Double?

    suspend fun getDieselPrice(uf: String): Double?

    suspend fun getNationalGasolinePrice(): Double?

}