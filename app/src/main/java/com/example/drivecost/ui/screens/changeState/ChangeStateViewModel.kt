package com.example.drivecost.ui.screens.changeState

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.drivecost.data.local.brazilianStates
import com.example.drivecost.model.BrazilianState

class ChangeStateViewModel : ViewModel() {

    private val _state = MutableLiveData("")
    val state: LiveData<String> = _state

    private val _filteredStates = MutableLiveData(brazilianStates)
    val filteredStates: LiveData<List<BrazilianState>> = _filteredStates

    private val _selectedState = MutableLiveData<BrazilianState?>(null)
    val selectedState: LiveData<BrazilianState?> = _selectedState

    private val _averageNationalPrice = MutableLiveData("6,75")
    val averageNationalPrice: LiveData<String> = _averageNationalPrice

    fun updateState(value: String) {
        _state.value = value
        searchState(value)
    }

    fun searchState(query: String) {
        _filteredStates.value = brazilianStates.filter { state ->
            state.name.contains(query, ignoreCase = true) ||
                    state.abbreviation.contains(query, ignoreCase = true)
        }
    }

    fun selectState(state: BrazilianState) {
        _selectedState.value = state
    }
}