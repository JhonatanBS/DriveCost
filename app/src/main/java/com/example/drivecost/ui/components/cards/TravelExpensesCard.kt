package com.example.drivecost.ui.components.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.drivecost.ui.components.common.CounterAction
import com.example.drivecost.ui.components.common.CounterButton
import com.example.drivecost.ui.components.common.DescriptionText
import com.example.drivecost.ui.components.common.TravelInputField
import com.example.drivecost.ui.components.layout.TravelSummaryCard

@Composable
fun TravelExpensesCard(
    tollAmount: String,
    onTollAmountChange: (String) -> Unit,
    peopleCount: String,
    onPeopleCountChange: (String) -> Unit
) {

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
                    DescriptionText(text = "Pedágios (R$)")
                }

                TravelInputField(
                    value = tollAmount,
                    onValueChange = onTollAmountChange,
                    placeholder = "R$"
                )
            }

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Box(
                    modifier = Modifier.padding(bottom = 10.dp)
                ) {
                    DescriptionText(text = "Pessoas")
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    CounterButton(
                        action = CounterAction.DECREMENT,
                        onClick = {})

                    Text(
                        text = peopleCount,
                        style = MaterialTheme.typography.bodyLarge
                    )

                    CounterButton(
                        action = CounterAction.INCREMENT,
                        onClick = {})

                }
            }
        }
    }
}