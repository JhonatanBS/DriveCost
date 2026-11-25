
package com.example.drivecost.ui.components.cards

import androidx.compose.foundation.layout.Arrangement
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
import java.util.Locale

@Composable
fun TravelExpensesCard(
    tollAmount: Double,
    onTollAmountChange: (Double) -> Unit,
    peopleCount: Int,
    incrementPeopleCount: () -> Unit,
    decrementPeopleCount: () -> Unit
) {
    val formattedTollAmount = if (tollAmount == 0.0) {
        ""
    } else {
        String.format(
            Locale.forLanguageTag("pt-BR"),
            "%.2f",
            tollAmount
        )
    }

    TravelSummaryCard {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                DescriptionText(
                    modifier = Modifier.padding(bottom = 10.dp),
                    text = "Pedágios (R$)"
                )

                TravelInputField(
                    value = formattedTollAmount,
                    onValueChange = { newValue ->
                        onTollAmountChange(
                            newValue.replace(",", ".").toDoubleOrNull() ?: 0.0
                        )
                    },
                    placeholder = "R$"
                )
            }

            Column(
                modifier = Modifier.weight(1f)
            ) {
                DescriptionText(
                    modifier = Modifier.padding(bottom = 10.dp),
                    text = "Pessoas"
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CounterButton(
                        action = CounterAction.DECREMENT,
                        onClick = decrementPeopleCount
                    )

                    Text(
                        text = peopleCount.toString(),
                        style = MaterialTheme.typography.bodyLarge
                    )

                    CounterButton(
                        action = CounterAction.INCREMENT,
                        onClick = incrementPeopleCount
                    )
                }
            }
        }
    }
}
