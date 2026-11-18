package com.example.drivecost.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.drivecost.ui.screens.changeState.ChangeStateScreen
import com.example.drivecost.ui.screens.changeState.ChangeStateViewModel
import com.example.drivecost.ui.screens.home.HomeScreen
import com.example.drivecost.ui.screens.home.HomeViewModel
import com.example.drivecost.ui.screens.splashscreen.SplashScreen
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
            val viewModel: HomeViewModel = viewModel()
            HomeScreen(
                viewModel = viewModel,
                onNavigateToChangeState = {
                    navController.navigate("changeState")
                })
        }

        composable("changeState") {
            val viewModel: ChangeStateViewModel = viewModel()
            ChangeStateScreen(
                viewModel = viewModel,
                onGoBackClick = {
                    navController.popBackStack()
                })
        }
    }
}