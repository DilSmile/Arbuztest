package com.example.arbuztest.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.arbuztest.presentation.basket.BasketViewModel

@Composable
fun MainScreen(
    bottomNavController: NavHostController,
    basketViewModel: BasketViewModel = hiltViewModel()
) {
    Scaffold(
        bottomBar = {
            Column {
                MainNavBar(bottomNavController,basketViewModel)
            }
        },
        contentWindowInsets = WindowInsets.navigationBars
    ) { paddingValues ->
        BottomBarNavGraph(
            modifier = Modifier.padding(paddingValues),
            navController = bottomNavController,
        )
    }
}
