package com.example.drivecost.ui.components.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.drivecost.ui.components.common.StateItem
import com.example.drivecost.model.State

private val states = listOf(
    State(id = 1, name = "São Paulo", abbreviation = "SP"),
    State(id = 2, name = "Rio de Janeiro", abbreviation = "RJ"),
    State(id = 3, name = "Minas Gerais", abbreviation = "MG"),
    State(id = 4, name = "Bahia", abbreviation = "BA"),
    State(id = 5, name = "Paraná", abbreviation = "PR"),
    State(id = 6, name = "Goiás", abbreviation = "GO"),
)

@Composable
fun StateSelectionListCard() {
    Surface(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.padding(top = 20.dp),
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(0.dp),
        ) {
            items(
                items = states,
                key = { it.id }
            ) { state ->
                StateItem(
                    state = state.name,
                    abbreviation = state.abbreviation
                ) {
                    // onStateSelected(state)
                }
            }
        }
    }
}