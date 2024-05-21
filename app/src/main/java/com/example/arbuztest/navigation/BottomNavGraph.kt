package com.example.arbuztest.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.arbuztest.presentation.basket.BasketScreen
import com.example.arbuztest.presentation.basket.BasketViewModel
import com.example.arbuztest.presentation.home.HomeScreen
import com.example.arbuztest.presentation.home.HomeViewModel

@Composable
fun BottomBarNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = MainDestinations.HomeScreen_route,
        modifier = modifier,
        enterTransition = {
            EnterTransition.None
        },
        exitTransition = {
            ExitTransition.None
        },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None }
    ) {
        composable(route = MainDestinations.HomeScreen_route) {
            val viewModel: HomeViewModel = hiltViewModel()

            HomeScreen(viewModel = viewModel)
        }
        composable(route = MainDestinations.BasketScreen_route) {
            val viewModel: BasketViewModel = hiltViewModel()
            BasketScreen(viewModel = viewModel)
        }
    }
}