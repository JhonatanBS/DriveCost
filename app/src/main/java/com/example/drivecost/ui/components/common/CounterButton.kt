package com.example.drivecost.ui.components.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.drivecost.R

enum class CounterAction {
    INCREMENT,
    DECREMENT
}

@Composable
fun CounterButton(
    action: CounterAction,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Button(
        onClick = onClick,
        modifier = modifier.size(55.dp),
        shape = RoundedCornerShape(9.dp),
        contentPadding = PaddingValues(0.dp),
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.background),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.onSecondary,
            contentColor = MaterialTheme.colorScheme.onSecondary,
        )
    ) {

        Icon(
            modifier = Modifier.size(18.dp),
            painter =
                painterResource(
                    when (action) {
                        CounterAction.INCREMENT -> R.drawable.ic_plus
                        CounterAction.DECREMENT -> R.drawable.ic_minus
                    }
                ),
            contentDescription = when (action) {
                CounterAction.INCREMENT -> "Add person"
                CounterAction.DECREMENT -> "Remove person"
            },
            tint = MaterialTheme.colorScheme.secondary,
        )

    }
}