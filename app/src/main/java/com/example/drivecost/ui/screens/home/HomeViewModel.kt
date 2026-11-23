package com.example.drivecost.ui.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.drivecost.repository.FuelRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val fuelRepository: FuelRepository) : ViewModel() {

    private val _distance = MutableLiveData(0)
    val distance: LiveData<Int> = _distance

    private val _consumption = MutableLiveData(0.00)
    val consumption: LiveData<Double> = _consumption

    private val _uf = MutableLiveData("")
    val uf: LiveData<String> = _uf

    private val _price = MutableLiveData(0.00)
    val price: LiveData<Double?> = _price

    private val _tollAmount = MutableLiveData(0.0)
    val tollAmount: LiveData<Double> = _tollAmount

    private val _peopleCount = MutableLiveData(1)
    val peopleCount: LiveData<Int> = _peopleCount

    private val _totalCostDistance = MutableLiveData(0.00)
    val totalCostDistance: LiveData<Double> = _totalCostDistance

    private val _totalCostConsumption = MutableLiveData(0.00)
    val totalCostConsumption: LiveData<Double> = _totalCostConsumption

    private val _totalCostLiters = MutableLiveData(0.00)
    val totalCostLiters: LiveData<Double> = _totalCostLiters

    private val _totalCostForPeople = MutableLiveData(0.00)
    val totalCostForPeople: LiveData<Double> = _totalCostForPeople

    fun updateDistance(value: Int) {
        _distance.value = value
        calculateFuelLiters()
        calculateFuelCost()
        calculateCostTotal()
        calculateCounterPeople()
    }

    fun updateConsumption(value: Double) {
        _consumption.value = value
        calculateFuelLiters()
        calculateFuelCost()
        calculateCostTotal()
        calculateCounterPeople()
    }

    fun updateTollAmount(value: Double) {
        _tollAmount.value = value
        calculateFuelLiters()
        calculateFuelCost()
        calculateCostTotal()
        calculateCounterPeople()
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
        calculateCounterPeople()
    }

    fun decrementPeopleCount() {
        _peopleCount.value = _peopleCount.value?.let {
            (it - 1).coerceAtLeast(1)
        }
        calculateCounterPeople()
    }

    fun calculateFuelLiters() {
        val distanceValue = distance.value ?: 0
        val consumptionValue = consumption.value ?: 0.0

        if (consumptionValue > 0.0 && distanceValue > 0) {
            _totalCostLiters.value = distanceValue / consumptionValue
        } else {
            _totalCostLiters.value = 0.0
        }
    }

    fun calculateFuelCost() {
        val litersValue = totalCostLiters.value ?: 0.0
        val priceValue = price.value ?: 0.0

        _totalCostConsumption.value = litersValue * priceValue
    }

    fun calculateCostTotal() {
        val tollAmountValue = tollAmount.value ?: 0.0
        val totalCostConsumption = totalCostConsumption.value ?: 0.0

        _totalCostDistance.value = tollAmountValue + totalCostConsumption
    }

    fun calculateCounterPeople() {
        val totalCostDistanceValue = totalCostDistance.value ?: 0.0
        val peopleCountValue = peopleCount.value ?: 0

        _totalCostForPeople.value = totalCostDistanceValue / peopleCountValue
    }
}