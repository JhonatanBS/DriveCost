package com.example.drivecost.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.drivecost.data.remote.client.RetrofitClient
import com.example.drivecost.data.repository.FuelRepositoryImplement
import com.example.drivecost.ui.screens.changeState.ChangeStateScreen
import com.example.drivecost.ui.screens.changeState.ChangeStateViewModel
import com.example.drivecost.ui.screens.changeState.ChangeStateViewModelFactory
import com.example.drivecost.ui.screens.home.HomeScreen
import com.example.drivecost.ui.screens.home.HomeViewModel
import com.example.drivecost.ui.screens.home.HomeViewModelFactory
import com.example.drivecost.ui.screens.splashscreen.SplashScreen
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    val fuelRepository = remember {
        FuelRepositoryImplement(
            fuelService = RetrofitClient.fuelService
        )
    }

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

        composable(
            route = "home?uf={uf}",
            arguments = listOf(
                navArgument("uf") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )
        ) { backStackEntry ->

            val uf = backStackEntry.arguments?.getString("uf")

            val factory = remember {
                HomeViewModelFactory(fuelRepository)
            }

            val viewModel: HomeViewModel = viewModel(
                factory = factory
            )

            HomeScreen(
                viewModel = viewModel,
                onNavigateToChangeState = {
                    navController.navigate("changeState")
                },
                selectedUf = uf
            )

        }

        composable("changeState") {
            val factory = remember {
                ChangeStateViewModelFactory(fuelRepository)
            }

            val viewModel: ChangeStateViewModel = viewModel(
                factory = factory
            )
            ChangeStateScreen(
                viewModel = viewModel,
                onGoBackClick = {
                    navController.popBackStack()
                },
                onSelectedPrice = {
                    val selectedState = viewModel.selectedState.value

                    selectedState?.let {
                        navController.navigate("home?uf=${it.uf}") {
                            popUpTo("home?uf={uf}") {
                                inclusive = true
                            }
                        }
                    }
                }
            )
        }
    }
}