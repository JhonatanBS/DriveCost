package com.example.drivecost

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.drivecost.ui.components.CounterAction
import com.example.drivecost.ui.components.CounterButton
import com.example.drivecost.ui.components.DescriptionText
import com.example.drivecost.ui.components.ScreenContainer
import com.example.drivecost.ui.components.ScreenHeader
import com.example.drivecost.ui.components.StateBadge
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

    var roundTrip by rememberSaveable {
        mutableStateOf(false)
    }

    var price by rememberSaveable {
        mutableStateOf("")
    }

    var tollAmount by rememberSaveable {
        mutableStateOf("")
    }

    var peopleCount by rememberSaveable {
        mutableStateOf("1")
    }

    ScreenContainer {

        ScreenHeader("Custo da Viagem", "Combustível, pedágio e divisão por pessoa.")

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

                    Box(
                        modifier = Modifier.padding(bottom = 10.dp)
                    ) {
                        DescriptionText("Distância (km)")
                    }

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

                    Box(
                        modifier = Modifier.padding(bottom = 10.dp)
                    ) {
                        DescriptionText("Consumo (km/l)")
                    }

                    TravelInputField(
                        consumption,
                        onValueChange = { consumption = it },
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
                    DescriptionText("Ida e volta", true)
                    DescriptionText("Dobra a distância")
                }

                Row(
                    modifier = Modifier
                        .weight(1f),
                    horizontalArrangement = Arrangement.End
                ) {

                    Switch(
                        modifier = Modifier.scale(1.2f),
                        checked = roundTrip,
                        onCheckedChange = { roundTrip = it },
                        colors = SwitchDefaults.colors(
                            uncheckedThumbColor = MaterialTheme.colorScheme.onSecondary,
                            uncheckedTrackColor = MaterialTheme.colorScheme.outline,
                            uncheckedBorderColor = MaterialTheme.colorScheme.outline
                        )
                    )
                }
            }
        }

        TravelSummaryCard {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {

                    Icon(
                        modifier = Modifier
                            .size(14.dp),
                        painter = painterResource(id = R.drawable.ic_gas),
                        contentDescription = "icon of gas",
                    )

                    DescriptionText("Preço do litro da gasolina")
                }

                StateBadge("DF")
            }

            TravelInputField(
                price,
                { price = it },
                "R$"
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                DescriptionText("Média da ANP no DF")

                Text(
                    text = "Alterar estado",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }

        TravelSummaryCard {

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {

                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    Box(
                        modifier = Modifier.padding(bottom = 10.dp)
                    ) {
                        DescriptionText("Pedágios (R$)")
                    }

                    TravelInputField(
                        value = tollAmount,
                        onValueChange = { tollAmount = it },
                        placeholder = "R$"
                    )
                }

                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    Box(
                        modifier = Modifier.padding(bottom = 10.dp)
                    ) {
                        DescriptionText("Pessoas")
                    }

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        CounterButton(CounterAction.DECREMENT) { }

                        Text(
                            text = peopleCount,
                            style = MaterialTheme.typography.bodyLarge
                        )

                        CounterButton(CounterAction.INCREMENT) { }

                    }
                }
            }
        }

        TravelSummaryCard(containerColor = MaterialTheme.colorScheme.primary) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Text(
                    text = "Custo total · 600 Km",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onPrimary
                )

                Text(
                    text = "R$ 355,00",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }

            HorizontalDivider(
                thickness = 0.5.dp,
                color = MaterialTheme.colorScheme.onPrimary
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.Top,
            ) {

                Column(
                ) {
                    Text(
                        text = "Combustível",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onPrimary
                    )

                    Text(
                        text = "R$ 325,00",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }

                Column(
                ) {
                    Text(
                        text = "Litros",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onPrimary
                    )

                    Text(
                        text = "50,00 L",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }

                Column(
                ) {

                    Text(
                        text = "Por pessoa",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onPrimary
                    )

                    Text(
                        text = "R$ 177,50",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }
    }
}