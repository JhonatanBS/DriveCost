package com.example.drivecost

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.drivecost.ui.components.cards.StateSelectionListCard
import com.example.drivecost.ui.components.common.GoBackButton
import com.example.drivecost.ui.components.common.TravelInputField
import com.example.drivecost.ui.components.layout.ScreenContainer
import com.example.drivecost.ui.components.layout.ScreenHeader
import com.example.drivecost.ui.navigation.AppNavigation
import com.example.drivecost.ui.theme.DriveCostTheme

class ChangeStateActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            DriveCostTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun ChangeStateScreen(onGoBackClick: () -> Unit) {

    var state by rememberSaveable {
        mutableStateOf("")
    }

    ScreenContainer {

        GoBackButton(onClick = onGoBackClick)

        ScreenHeader(
            modifier = Modifier.padding(bottom = 20.dp),
            title = "Preço da gasolina",
            subtitle = "Escolha o estado para usar a média semanal da ANP"
        )

        TravelInputField(
            value = state,
            onValueChange = { state = it },
            placeholder = "Buscar estado ou cidade"
        )

        StateSelectionListCard()
    }
}