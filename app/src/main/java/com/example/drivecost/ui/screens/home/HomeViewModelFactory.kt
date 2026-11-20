
package com.example.drivecost.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.drivecost.repository.FuelRepository

class HomeViewModelFactory(
    private val fuelRepository: FuelRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(fuelRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
