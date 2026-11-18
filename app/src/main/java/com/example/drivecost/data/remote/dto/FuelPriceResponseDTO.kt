package com.example.drivecost.data.remote.dto

data class FuelPriceResponseDTO(
    val error: Boolean,
    val message: String,
    val data_coleta: String,
    val fonte: String,
    val moeda: String,
    val tempo_execucao_segundos: Double,
    val precos: FuelPricesDTO,
    val icones: Map<String, String>,
    val analise: FuelAnalysisDTO
)