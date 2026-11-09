package com.example.drivecost

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import com.example.drivecost.ui.components.ScreenContainer
import com.example.drivecost.ui.theme.DriveCostTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            DriveCostTheme {
                HomeScreen()
            }
        }
    }
}

@Composable
fun HomeScreen() {
    ScreenContainer {}
}