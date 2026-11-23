package com.example.drivecost.ui.screens.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
fun HomeScreen(
    viewModel: HomeViewModel,
    onNavigateToChangeState: () -> Unit,
    selectedUf: String?
) {

    val distance by viewModel.distance.observeAsState(0)
    val consumption by viewModel.consumption.observeAsState(0.0)

    val uf by viewModel.uf.observeAsState(selectedUf)
    val price by viewModel.price.observeAsState(null)
    val tollAmount by viewModel.tollAmount.observeAsState(0.0)
    val peopleCount by viewModel.peopleCount.observeAsState(1)

    val totalCostDistance by viewModel.totalCostDistance.observeAsState(0.0)
    val totalCostConsumption by viewModel.totalCostConsumption.observeAsState(0.0)
    val totalCostLiters by viewModel.totalCostLiters.observeAsState(0.0)
    val totalCostForPeople by viewModel.totalCostForPeople.observeAsState(0.0)

    var roundTrip by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(selectedUf) {
        viewModel.loadingGasolinePrice(selectedUf ?: "br")
    }

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
            selectedUf = uf,
            onNavigateToChangeState = onNavigateToChangeState,
        )

        TravelExpensesCard(
            tollAmount = tollAmount,
            onTollAmountChange = viewModel::updateTollAmount,
            peopleCount = peopleCount,
            incrementPeopleCount = viewModel::incrementPeopleCount,
            decrementPeopleCount = viewModel::decrementPeopleCount
        )

        TripCostSummaryCard(
            totalCostDistance = totalCostDistance,
            totalCostConsumption = totalCostConsumption,
            totalCostLiters = totalCostLiters,
            totalCostForPeople = totalCostForPeople,
            distance = distance,
            roundTrip = roundTrip
        )
    }
}