package com.example.drivecost.ui.components.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StateItem(
    state: String,
    abbreviation: String,
    isSelected: Boolean = false,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.onPrimary
        ),
        border = BorderStroke(0.dp, MaterialTheme.colorScheme.background),
        shape = RoundedCornerShape(0.dp),
        contentPadding = PaddingValues(0.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp , vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = state,
                style = if (isSelected) MaterialTheme.typography.bodyMedium else MaterialTheme.typography.bodySmall,
                color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
            )

            Text(
                text = abbreviation,
                style = if (isSelected) MaterialTheme.typography.bodyMedium else MaterialTheme.typography.labelLarge,
                color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
            )

        }
    }
}