package com.example.drivecost.ui.screens.changeState

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.drivecost.repository.FuelRepository

class ChangeStateViewModelFactory(
    private val fuelRepository: FuelRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChangeStateViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ChangeStateViewModel(fuelRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}