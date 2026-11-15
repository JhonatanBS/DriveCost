package com.example.drivecost.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
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
fun CounterButton(type: CounterAction, onPeopleCountChange: () -> Unit) {

    Button(
        onClick = onPeopleCountChange,
        modifier = Modifier.size(55.dp),
        shape = RoundedCornerShape(9.dp),
        contentPadding = PaddingValues(0.dp),
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.background),
        colors = ButtonColors(
            containerColor = MaterialTheme.colorScheme.onSecondary,
            contentColor = MaterialTheme.colorScheme.onSecondary,
            disabledContainerColor = MaterialTheme.colorScheme.onSecondary,
            disabledContentColor = MaterialTheme.colorScheme.onSecondary
        )
    ) {

        Icon(
            modifier = Modifier.size(18.dp),
            painter =
                painterResource(
                    if (type == CounterAction.INCREMENT) R.drawable.ic_plus
                    else R.drawable.ic_minus
                ),
            contentDescription = "Add people from travel",
            tint = MaterialTheme.colorScheme.secondary,
        )

    }
}