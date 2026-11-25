
package com.example.drivecost.ui.components.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.drivecost.ui.components.layout.TravelSummaryCard
import java.util.Locale

@Composable
fun TripCostSummaryCard(
    totalCostDistance: Double,
    totalCostConsumption: Double,
    totalCostLiters: Double,
    totalCostForPeople: Double,
    distance: Int,
    roundTrip: Boolean,
    modifier: Modifier = Modifier
) {
    TravelSummaryCard(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Custo total • ${if(roundTrip) distance * 2 else distance} km",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onPrimary
            )

            Text(
                text = formatCurrency(totalCostDistance),
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
            verticalAlignment = Alignment.Top
        ) {
            SummaryMetric(
                label = "Combustível",
                value = formatCurrency(totalCostConsumption)
            )

            SummaryMetric(
                label = "Litros",
                value = "${formatNumber(totalCostLiters)} L"
            )

            SummaryMetric(
                label = "Por pessoa",
                value = formatCurrency(totalCostForPeople)
            )
        }
    }
}

@Composable
private fun SummaryMetric(
    label: String,
    value: String
) {
    Column {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onPrimary
        )

        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

private fun formatCurrency(value: Double): String {
    return String.format(
        Locale.forLanguageTag("pt-BR"),
        "R$ %.2f",
        value
    )
}

private fun formatNumber(value: Double): String {
    return String.format(
        Locale.forLanguageTag("pt-BR"),
        "%.2f",
        value
    )
}
