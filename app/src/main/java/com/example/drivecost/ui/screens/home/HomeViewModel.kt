package com.example.drivecost.ui.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.drivecost.repository.FuelRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val fuelRepository: FuelRepository) : ViewModel() {

    private val _distance = MutableLiveData("")
    val distance: LiveData<String> = _distance

    private val _consumption = MutableLiveData("")
    val consumption: LiveData<String> = _consumption

    private val _uf = MutableLiveData("")
    val uf: LiveData<String> = _uf

    private val _price = MutableLiveData(0.00)
    val price: LiveData<Double?> = _price

    private val _tollAmount = MutableLiveData("")
    val tollAmount: LiveData<String> = _tollAmount

    private val _peopleCount = MutableLiveData(1)
    val peopleCount: LiveData<Int> = _peopleCount

    fun updateDistance(value: String) {
        _distance.value = value
    }

    fun updateConsumption(value: String) {
        _consumption.value = value
    }

    fun updateTollAmount(value: String) {
        _tollAmount.value = value
    }

    fun loadingGasolinePrice(uf: String = "br") {
        viewModelScope.launch {
            val price = fuelRepository.getGasolinePrice(uf)
            _price.value = price
            _uf.value = uf.uppercase()
        }
    }

    fun incrementPeopleCount() {
        _peopleCount.value = _peopleCount.value?.plus(1)
    }

    fun decrementPeopleCount() {
        _peopleCount.value = _peopleCount.value?.let {
            (it - 1).coerceAtLeast(1)
        }
    }
}