package com.example.drivecost.ui.components.cards

import androidx.compose.foundation.layout.Arrangement
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
import com.example.drivecost.model.BrazilianState
import com.example.drivecost.ui.components.common.StateItem

@Composable
fun StateSelectionListCard(
    filteredStates: List<BrazilianState>,
    selectedState: BrazilianState?,
    onStateClick: (BrazilianState) -> Unit
) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.padding(top = 20.dp),
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 260.dp),
            verticalArrangement = Arrangement.spacedBy(0.dp),
        ) {
            items(
                items = filteredStates,
                key = { it.abbreviation }
            ) { state ->

                val isSelected = selectedState?.abbreviation == state.abbreviation
                StateItem(
                    state = state.name,
                    abbreviation = state.abbreviation,
                    isSelected = isSelected,
                    onClick = {
                        onStateClick(state)
                    }
                )
            }
        }
    }
}