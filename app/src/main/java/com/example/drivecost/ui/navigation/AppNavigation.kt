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

private object AppRoutes {
    const val SPLASH = "splashscreen"
    const val HOME = "home"
    const val HOME_WITH_UF = "home?uf={uf}"
    const val CHANGE_STATE = "changeState"
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    val fuelRepository = remember {
        FuelRepositoryImplement(
            fuelService = RetrofitClient.fuelService
        )
    }

    val homeViewModelFactory = remember(fuelRepository) {
        HomeViewModelFactory(fuelRepository)
    }

    val changeStateViewModelFactory = remember(fuelRepository) {
        ChangeStateViewModelFactory(fuelRepository)
    }

    NavHost(
        navController = navController,
        startDestination = AppRoutes.SPLASH
    ) {
        composable(route = AppRoutes.SPLASH) {
            SplashScreen()

            LaunchedEffect(Unit) {
                delay(1500.milliseconds)

                navController.navigate(AppRoutes.HOME) {
                    popUpTo(AppRoutes.SPLASH) {
                        inclusive = true
                    }
                }
            }
        }

        composable(
            route = AppRoutes.HOME_WITH_UF,
            arguments = listOf(
                navArgument("uf") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )
        ) { backStackEntry ->

            val uf = backStackEntry.arguments?.getString("uf")

            val homeViewModel: HomeViewModel = viewModel(
                factory = homeViewModelFactory
            )

            HomeScreen(
                viewModel = homeViewModel,
                selectedUf = uf,
                onNavigateToChangeState = {
                    navController.navigate(AppRoutes.CHANGE_STATE)
                }
            )
        }

        composable(AppRoutes.CHANGE_STATE) {

            val changeStateViewModel: ChangeStateViewModel = viewModel(
                factory = changeStateViewModelFactory
            )

            ChangeStateScreen(
                viewModel = changeStateViewModel,
                onGoBackClick = {
                    navController.popBackStack()
                },
                onSelectedPrice = {
                    val selectedState = changeStateViewModel.selectedState.value

                    selectedState?.let {
                        navController.navigate("${AppRoutes.HOME}?uf=${it.uf}") {
                            popUpTo(route = AppRoutes.HOME_WITH_UF) {
                                inclusive = true
                            }
                        }
                    }
                }
            )
        }
    }
}