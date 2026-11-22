package com.example.drivecost.model

data class StateFuelInformation(
    val flagUrl: String,
    val state: String,
    val uf: String,
    val gasoline: Double?,
    val diesel: Double?,
)