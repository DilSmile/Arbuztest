package com.example.arbuztest.navigation


import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MainNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    bottomNavController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = MainDestinations.MainScreen_route,
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
        composable(route = MainDestinations.MainScreen_route) {
            MainScreen(
                bottomNavController = bottomNavController
            )
        }
    }
}

private object MainScreens {
    const val HomeScreen = "HomeScreen"
    const val BasketScreen = "BasketScreen"
    const val MainScreen = "MainScreen"
}

object MainDestinations {
    const val HomeScreen_route = MainScreens.HomeScreen
    const val BasketScreen_route = MainScreens.BasketScreen
    const val MainScreen_route = MainScreens.MainScreen
}