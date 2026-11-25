package com.example.drivecost.ui.components.common

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle

@Composable
fun DescriptionText(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle = MaterialTheme.typography.labelSmall
) {
    Text(
        modifier = modifier,
        text = text,
        style = style,
        color = MaterialTheme.colorScheme.secondary
    )
}