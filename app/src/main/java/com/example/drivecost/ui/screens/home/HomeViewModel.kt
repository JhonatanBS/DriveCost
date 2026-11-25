package com.example.drivecost.ui.screens.home

import android.util.Log
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

    private val _roundTrip = MutableLiveData(false)
    val roundTrip: LiveData<Boolean> = _roundTrip

    fun updateRoundTrip(value: Boolean) {
        _roundTrip.value = value
        recalculateFuelCost()
    }

    fun updateDistance(value: Int) {
        _distance.value = value
        recalculateFuelCost()
    }

    fun updateConsumption(value: Double) {
        _consumption.value = value
        recalculateFuelCost()
    }

    fun updateTollAmount(value: Double) {
        _tollAmount.value = value
        recalculateFuelCost()
    }

    fun loadGasolinePrice(uf: String = "br") {
        viewModelScope.launch {
            try {
                val price = fuelRepository.getGasolinePrice(uf)

                _price.value = price
                _uf.value = uf.uppercase()

                calculateFuelCost()
            } catch (exception: Exception) {
                Log.e(
                    "DRIVECOST",
                    "Error fetching gasoline price",
                    exception
                )
            }
        }
    }

    fun incrementPeopleCount() {
        _peopleCount.value = (_peopleCount.value ?: 1) + 1
        calculateCostForPeople()
    }

    fun decrementPeopleCount() {
        _peopleCount.value = (_peopleCount.value ?: 1)
            .minus(1)
            .coerceAtLeast(1)

        calculateCostForPeople()
    }

    private fun recalculateFuelCost() {
        calculateFuelLiters()
        calculateFuelCost()
    }

    private fun calculateFuelLiters() {
        val distanceValue = distance.value ?: 0
        val consumptionValue = consumption.value ?: 0.0
        val multiplier = if (roundTrip.value == true) 2 else 1

        _totalCostLiters.value =
            if (distanceValue > 0 && consumptionValue > 0.0) {
                (distanceValue * multiplier) / consumptionValue
            } else {
                0.0
            }
    }

    private fun calculateFuelCost() {
        val litersValue = totalCostLiters.value ?: 0.0
        val priceValue = price.value ?: 0.0

        _totalCostConsumption.value = litersValue * priceValue

        calculateCostTotal()
    }

    private fun calculateCostTotal() {
        val tollAmountValue = tollAmount.value ?: 0.0
        val totalCostConsumption = totalCostConsumption.value ?: 0.0
        val multiplier = if (roundTrip.value == true) 2 else 1

        _totalCostDistance.value = (tollAmountValue * multiplier) + totalCostConsumption

        calculateCostForPeople()
    }

    private fun calculateCostForPeople() {
        val totalCost = totalCostDistance.value ?: 0.0
        val people = (peopleCount.value ?: 1).coerceAtLeast(1)

        _totalCostForPeople.value = totalCost / people
    }
}