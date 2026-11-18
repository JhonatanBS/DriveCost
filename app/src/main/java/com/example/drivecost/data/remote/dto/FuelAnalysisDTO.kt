package com.example.drivecost.data.remote.dto

data class FuelAnalysisDTO(
    val estado_mais_barato_gasolina: StateFuelAnalysisDTO,
    val estado_mais_caro_gasolina: StateFuelAnalysisDTO,
    val diferenca_gasolina: String,
    val variacao_percentual_gasolina: String,
    val estado_mais_barato_diesel: StateFuelAnalysisDTO,
    val estado_mais_caro_diesel: StateFuelAnalysisDTO,
    val diferenca_diesel: String,
    val variacao_percentual_diesel: String
)