package com.example.drivecost.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.drivecost.ChangeStateScreen
import com.example.drivecost.HomeScreen
import com.example.drivecost.SplashScreen
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splashscreen") {
        composable("splashscreen") {
            SplashScreen()

            LaunchedEffect(Unit) {
                delay(1500.milliseconds)

                navController.navigate("home") {
                    popUpTo("splashscreen") {
                        inclusive = true
                    }
                }
            }
        }

        composable("home") {
            HomeScreen(
                onNavigateToChangeState = {
                    navController.navigate("changeState")
                })
        }

        composable("changeState") {
            ChangeStateScreen(onGoBackClick = {
                navController.popBackStack()
            })
        }
    }
}