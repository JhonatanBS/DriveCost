package com.example.drivecost.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun DescriptionText(text: String, isBold: Boolean = false) {
    Text(
        text = text,
        style = if (isBold) MaterialTheme.typography.labelLarge else MaterialTheme.typography.labelSmall,
        color = MaterialTheme.colorScheme.secondary
    )
}