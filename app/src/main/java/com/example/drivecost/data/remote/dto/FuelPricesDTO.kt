package com.example.drivecost.data.remote.dto

data class FuelPricesDTO(
    val gasolina: Map<String, String>,
    val diesel: Map<String, String>
)