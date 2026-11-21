package com.example.drivecost.ui.screens.changeState

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.drivecost.data.local.brazilianStates
import com.example.drivecost.model.BrazilianState
import com.example.drivecost.repository.FuelRepository
import kotlinx.coroutines.launch

class ChangeStateViewModel(private val fuelRepository: FuelRepository) : ViewModel() {

    private val _state = MutableLiveData("")
    val state: LiveData<String> = _state

    private val _filteredStates = MutableLiveData(brazilianStates)
    val filteredStates: LiveData<List<BrazilianState>> = _filteredStates

    private val _selectedState = MutableLiveData<BrazilianState?>(null)
    val selectedState: LiveData<BrazilianState?> = _selectedState

    private val _averageNationalPrice = MutableLiveData(0.00)
    val averageNationalPrice: LiveData<Double?> = _averageNationalPrice

    init {
        loadNationalGasolinePrice()
    }

    fun updateState(value: String) {
        _state.value = value

        _filteredStates.value = brazilianStates.filter { state ->
            state.name.contains(value, ignoreCase = true) ||
                    state.abbreviation.contains(value, ignoreCase = true)
        }
    }

    fun selectState(state: BrazilianState) {
        _selectedState.value = state
    }

    fun loadNationalGasolinePrice() {
        viewModelScope.launch {
            try {
                val price = fuelRepository.getNationalGasolinePrice()
                _averageNationalPrice.value = price
            } catch (exception: Exception) {
                Log.e("DRIVECOST", "Error fetching gasoline price", exception)
            }
        }
    }
}