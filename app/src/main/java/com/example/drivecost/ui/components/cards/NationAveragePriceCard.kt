package com.example.drivecost.ui.components.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.drivecost.R
import com.example.drivecost.ui.components.common.DescriptionText
import com.example.drivecost.ui.components.common.StateBadge
import com.example.drivecost.ui.components.layout.TravelSummaryCard

@Composable
fun NationAveragePriceCard(value: String) {
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

                DescriptionText(text = "Média da gasolina comum")
            }

            StateBadge("BR")
        }

        Column {

            Text(
                text = value,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground
            )

            DescriptionText(text = "Semana de referência: R$ 6,75")
            DescriptionText(text = "Levantamento de Preço de Combustíveis, pesquisa semana da ANP")
        }
    }
}