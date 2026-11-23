package com.example.drivecost.ui.components.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import com.example.drivecost.ui.components.common.DescriptionText
import com.example.drivecost.ui.components.common.TravelInputField
import com.example.drivecost.ui.components.layout.TravelSummaryCard

@Composable
fun DistanceConsumptionCard(
    distance: Int,
    onDistanceChange: (Int) -> Unit,
    consumption: Double,
    onConsumptionChange: (Double) -> Unit,
    roundTrip: Boolean,
    onRoundTripChange: (Boolean) -> Unit
) {

    TravelSummaryCard {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Column(
                Modifier
                    .weight(1f),
                horizontalAlignment = Alignment.Start
            ) {

                DescriptionText(
                    modifier = Modifier.padding(bottom = 10.dp),
                    text = "Distância (km)"

                )

                TravelInputField(
                    value = if (distance == 0) "" else distance.toString(),
                    onValueChange = { newValue ->
                        onDistanceChange(newValue.toIntOrNull() ?: 0)
                    },
                    placeholder = "km",
                )
            }

            Column(
                Modifier
                    .weight(1f),
                horizontalAlignment = Alignment.Start

            ) {

                DescriptionText(
                    modifier = Modifier.padding(bottom = 10.dp),
                    text = "Consumo (km/l)"
                )

                TravelInputField(
                    value = if (consumption == 0.0) "" else consumption.toString(),
                    onValueChange = { newValue ->
                        onConsumptionChange(
                            newValue.replace(",", ".").toDoubleOrNull() ?: 0.0
                        )
                    },
                    placeholder = "km/l",
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                DescriptionText(text = "Ida e volta", isBold = true)
                DescriptionText(text = "Dobra a distância")
            }

            Row(
                modifier = Modifier
                    .weight(1f),
                horizontalArrangement = Arrangement.End
            ) {

                Switch(
                    modifier = Modifier.scale(1.2f),
                    checked = roundTrip,
                    onCheckedChange = onRoundTripChange,
                    colors = SwitchDefaults.colors(
                        uncheckedThumbColor = MaterialTheme.colorScheme.onSecondary,
                        uncheckedTrackColor = MaterialTheme.colorScheme.outline,
                        uncheckedBorderColor = MaterialTheme.colorScheme.outline
                    )
                )
            }
        }
    }
}