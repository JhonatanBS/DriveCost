package com.example.drivecost.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DescriptionText(text: String, isBold: Boolean = false) {
    Text(
        modifier = Modifier.padding(bottom = if (isBold) 0.dp else 10.dp),
        text = text,
        style = if (isBold) MaterialTheme.typography.labelLarge else MaterialTheme.typography.labelSmall,
        color = MaterialTheme.colorScheme.secondary
    )
}