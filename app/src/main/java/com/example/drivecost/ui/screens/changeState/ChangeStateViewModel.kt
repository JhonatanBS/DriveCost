package com.example.drivecost.ui.screens.changeState

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.drivecost.model.StateFuelInformation
import com.example.drivecost.repository.FuelRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch

class ChangeStateViewModel(
    private val fuelRepository: FuelRepository
) : ViewModel() {

    private val _state = MutableLiveData("")
    val state: LiveData<String> = _state

    private val _filteredStates =
        MutableLiveData<List<StateFuelInformation>>(emptyList())
    val filteredStates: LiveData<List<StateFuelInformation>> = _filteredStates

    private val _stateFuelInformation =
        MutableLiveData<List<StateFuelInformation>>(emptyList())
    val stateFuelInformation: LiveData<List<StateFuelInformation>> = _stateFuelInformation

    private val _selectedState = MutableLiveData<StateFuelInformation?>(null)
    val selectedState: LiveData<StateFuelInformation?> = _selectedState

    private val _averageNationalPrice = MutableLiveData<Double?>(null)
    val averageNationalPrice: LiveData<Double?> = _averageNationalPrice

    init {
        loadStates()
        loadNationalGasolinePrice()
    }

    private fun loadStates() {
        viewModelScope.launch {
            try {
                val states = fuelRepository.getStatesWithFuelInfo()

                _stateFuelInformation.value = states
                filterStates(_state.value.orEmpty())

            } catch (exception: CancellationException) {
                throw exception
            } catch (exception: Exception) {
                Log.e(TAG, "Error fetching state fuel information", exception)
            }
        }
    }

    fun updateState(value: String) {
        _state.value = value
        filterStates(value)
    }

    private fun filterStates(value: String) {
        _filteredStates.value = _stateFuelInformation.value.orEmpty().filter { currentState ->
            currentState.state.contains(value, ignoreCase = true) ||
                    currentState.uf.contains(value, ignoreCase = true)
        }
    }

    fun selectState(state: StateFuelInformation) {
        _selectedState.value = state
    }

    private fun loadNationalGasolinePrice() {
        viewModelScope.launch {
            try {
                val price = fuelRepository.getNationalGasolinePrice()
                _averageNationalPrice.value = price

            } catch (exception: CancellationException) {
                throw exception
            } catch (exception: Exception) {
                Log.e(TAG, "Error fetching national gasoline price", exception)
            }
        }
    }

    private companion object {
        const val TAG = "ChangeStateViewModel"
    }
}