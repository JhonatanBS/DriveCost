package com.example.drivecost.ui.screens.changeState

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ChangeStateViewModel : ViewModel() {

    private val _state = MutableLiveData("")
    val state: LiveData<String> = _state

    private val _averageNationalPrice = MutableLiveData("6,75")
    val averageNationalPrice: LiveData<String> = _averageNationalPrice

    fun updateState(value: String) {
        _state.value = value
    }
}