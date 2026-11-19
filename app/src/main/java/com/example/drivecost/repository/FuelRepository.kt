package com.example.drivecost.repository

import com.example.drivecost.model.State

interface FuelRepository {

    suspend fun getStates(): List<State>

    suspend fun getGasolinePrice(uf: String): Double?

    suspend fun getDieselPrice(uf: String): Double?

    suspend fun getNationalGasolinePrice(): Double?

}