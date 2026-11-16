package com.example.drivecost

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
fun HomeScreen() {

    var distance by rememberSaveable { mutableStateOf("") }
    var consumption by rememberSaveable { mutableStateOf("") }
    var roundTrip by rememberSaveable { mutableStateOf(false) }
    var price by rememberSaveable { mutableStateOf("") }
    var tollAmount by rememberSaveable { mutableStateOf("") }
    var peopleCount by rememberSaveable { mutableStateOf("1") }

    ScreenContainer {

        ScreenHeader(
            title = "Custo da Viagem",
            subtitle = "Combustível, pedágio e divisão por pessoa."
        )

        DistanceConsumptionCard(
            distance = distance,
            onDistanceChange = { distance = it },
            consumption = consumption,
            onConsumptionChange = { consumption = it },
            roundTrip = roundTrip,
            onRoundTripChange = { roundTrip = it }
        )

        FuelPriceCard(
            price = price,
            onPriceChange = { price = it }
        )

        TravelExpensesCard(
            tollAmount = tollAmount,
            onTollAmountChange = { tollAmount = it },
            peopleCount = peopleCount,
            onPeopleCountChange = { peopleCount = it }
        )

        TripCostSummaryCard(
            totalCost = "",
            fuelCost = "",
            fuelLiters = "",
            costPerPerson = ""
        )
    }
}