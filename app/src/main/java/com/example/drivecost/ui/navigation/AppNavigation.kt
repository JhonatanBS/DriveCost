package com.example.drivecost.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.drivecost.ChangeStateScreen
import com.example.drivecost.HomeScreen
import com.example.drivecost.SplashScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splashscreen") {
        composable("splashscreen") { SplashScreen() }

        composable("home") {
            HomeScreen(
                onNavigateToChangeState = {
                    navController.navigate("changeState")
                })
        }

        composable("changeState") { ChangeStateScreen() }
    }
}