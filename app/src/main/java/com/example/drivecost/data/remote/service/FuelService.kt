package com.example.drivecost.data.remote.service

import com.example.drivecost.data.remote.dto.FuelPriceResponseDTO
import retrofit2.Call
import retrofit2.http.GET

interface FuelService {
    @GET("api/precos")
    suspend fun getFuelBrazil(): Call<FuelPriceResponseDTO>
}