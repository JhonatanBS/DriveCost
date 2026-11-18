package com.example.drivecost.data.remote.client

import com.example.drivecost.data.remote.service.FuelService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://combustivelapi.com.br/")
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
    }
    val fuelService: FuelService by lazy {
        retrofit.create(FuelService::class.java)
    }
}