package com.example.drivecost.ui.components.cards

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.drivecost.model.StateFuelInformation
import com.example.drivecost.ui.components.common.StateItem

@Composable
fun StateSelectionListCard(
    states: List<StateFuelInformation>,
    selectedState: StateFuelInformation?,
    onStateClick: (StateFuelInformation) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.padding(top = 20.dp),
    ) {
        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .heightIn(max = 330.dp),
        ) {
            items(
                items = states,
                key = { it.uf }
            ) { state ->

                val isSelected = selectedState?.uf == state.uf
                StateItem(
                    state = state.state,
                    uf = state.uf,
                    isSelected = isSelected,
                    onClick = {
                        onStateClick(state)
                    },
                    flagUrl = state.flagUrl,
                    gasoline = state.gasoline,
                    diesel = state.diesel.toString()
                )
            }
        }
    }
}