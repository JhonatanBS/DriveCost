package com.example.drivecost.ui.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _distance = MutableLiveData("")
    val distance: LiveData<String> = _distance

    private val _consumption = MutableLiveData("")
    val consumption: LiveData<String> = _consumption

    private val _price = MutableLiveData("")
    val price: LiveData<String> = _price

    private val _tollAmount = MutableLiveData("")
    val tollAmount: LiveData<String> = _tollAmount

    private val _peopleCount = MutableLiveData("1")
    val peopleCount: LiveData<String> = _peopleCount

    fun updateDistance(value: String) {
        _distance.value = value
    }

    fun updateConsumption(value: String) {
        _consumption.value = value
    }

    fun updatePrice(value: String) {
        _price.value = value
    }

    fun updateTollAmount(value: String) {
        _tollAmount.value = value
    }

    fun updatePeopleCount(value: String) {
        _peopleCount.value = value
    }

}