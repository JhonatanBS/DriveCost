package com.example.drivecost.ui.components.common

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DescriptionText(
    modifier: Modifier = Modifier,
    text: String,
    isBold: Boolean = false,
) {
    Text(
        modifier = modifier,
        text = text,
        style = if (isBold) MaterialTheme.typography.labelLarge else MaterialTheme.typography.labelSmall,
        color = MaterialTheme.colorScheme.secondary
    )
}