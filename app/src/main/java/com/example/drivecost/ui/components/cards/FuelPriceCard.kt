package com.example.drivecost.ui.components.cards

import androidx.compose.foundation.layout.Arrangement
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
import com.example.drivecost.ui.components.common.TravelInputField
import com.example.drivecost.ui.components.layout.TravelSummaryCard

@Composable
fun FuelPriceCard(
    price: String,
    onPriceChange: (String) -> Unit
) {
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

                DescriptionText(text = "Preço do litro da gasolina")
            }

            StateBadge("DF")
        }

        TravelInputField(
            value = price,
            onValueChange = onPriceChange,
            placeholder = "R$"
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            DescriptionText(text = "Média da ANP no DF")

            Text(
                text = "Alterar estado",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}