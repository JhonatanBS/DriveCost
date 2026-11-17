package com.example.drivecost.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.drivecost.ChangeStateScreen
import com.example.drivecost.HomeScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                onNavigateToChangeState = {
                    navController.navigate("changeState")
                })
        }
        composable("changeState") { ChangeStateScreen() }
    }
}