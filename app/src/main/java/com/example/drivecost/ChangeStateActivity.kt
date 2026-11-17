package com.example.drivecost

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import com.example.drivecost.ui.components.layout.ScreenContainer
import com.example.drivecost.ui.navigation.AppNavigation
import com.example.drivecost.ui.theme.DriveCostTheme

class ChangeStateActivity : ComponentActivity(){
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
fun ChangeStateScreen() {
    ScreenContainer {

    }
}