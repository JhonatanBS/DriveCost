package com.example.drivecost.ui.screens.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.example.drivecost.ui.components.cards.DistanceConsumptionCard
import com.example.drivecost.ui.components.cards.FuelPriceCard
import com.example.drivecost.ui.components.layout.ScreenContainer
import com.example.drivecost.ui.components.layout.ScreenHeader
import com.example.drivecost.ui.components.cards.TravelExpensesCard
import com.example.drivecost.ui.components.cards.TripCostSummaryCard
import com.example.drivecost.ui.navigation.AppNavigation
import com.example.drivecost.ui.theme.DriveCostTheme
import androidx.compose.runtime.livedata.observeAsState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            DriveCostTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun HomeScreen(viewModel: HomeViewModel, onNavigateToChangeState: () -> Unit) {

    val distance by viewModel.distance.observeAsState("")
    val consumption by viewModel.consumption.observeAsState("")

    val price by viewModel.price.observeAsState("")
    val tollAmount by viewModel.tollAmount.observeAsState("")
    val peopleCount by viewModel.peopleCount.observeAsState("")

    var roundTrip by rememberSaveable { mutableStateOf(false) }

    ScreenContainer {

        ScreenHeader(
            title = "Custo da Viagem",
            subtitle = "Combustível, pedágio e divisão por pessoa."
        )

        DistanceConsumptionCard(
            distance = distance,
            onDistanceChange = viewModel::updateDistance,
            consumption = consumption,
            onConsumptionChange = viewModel::updateConsumption,
            roundTrip = roundTrip,
            onRoundTripChange = { roundTrip = it }
        )

        FuelPriceCard(
            price = price,
            onPriceChange = viewModel::updatePrice,
            onNavigateToChangeState = onNavigateToChangeState
        )

        TravelExpensesCard(
            tollAmount = tollAmount,
            onTollAmountChange = viewModel::updateTollAmount,
            peopleCount = peopleCount,
            onPeopleCountChange = viewModel::updatePeopleCount
        )

        TripCostSummaryCard(
            totalCost = "",
            fuelCost = "",
            fuelLiters = "",
            costPerPerson = ""
        )
    }
}