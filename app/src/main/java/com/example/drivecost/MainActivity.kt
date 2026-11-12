package com.example.drivecost

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.drivecost.ui.components.DescriptionText
import com.example.drivecost.ui.components.ScreenContainer
import com.example.drivecost.ui.components.ScreenHeader
import com.example.drivecost.ui.components.TravelInputField
import com.example.drivecost.ui.components.TravelSummaryCard
import com.example.drivecost.ui.theme.DriveCostTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            DriveCostTheme {
                HomeScreen()
            }
        }
    }
}

@Composable
fun HomeScreen() {

    var distance by rememberSaveable {
        mutableStateOf("")
    }

    var consumption by rememberSaveable {
        mutableStateOf("")
    }

    ScreenContainer {
        ScreenHeader("Custo da Viagem", "Combustível, pedágio e divisão por pessoa.")

        TravelSummaryCard {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Column(
                    Modifier
                        .weight(1f),
                    horizontalAlignment = Alignment.Start
                ) {
                    DescriptionText("Distância (km)")
                    TravelInputField(
                        distance,
                        onValueChange = { distance = it },
                        placeholder = "km",
                    )
                }
                Column(
                    Modifier
                        .weight(1f),
                    horizontalAlignment = Alignment.Start

                ) {
                    DescriptionText("Consumo (km/l)")
                    TravelInputField(
                        consumption,
                        onValueChange = { consumption = it },
                        placeholder = "km/l",
                    )
                }
            }
        }
    }
}