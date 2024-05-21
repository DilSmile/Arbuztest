package com.example.arbuztest.navigation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.arbuztest.R
import com.example.arbuztest.presentation.basket.BasketViewModel
import com.example.arbuztest.ui.theme.BottomBarColor
import com.example.arbuztest.ui.theme.Dark900
import com.example.arbuztest.ui.theme.Gray600
import com.example.arbuztest.utils.clickableWithoutRipple

@Composable
fun MainNavBar(
    navController: NavHostController,
    basketViewModel: BasketViewModel // Injected via Hilt
) {
    val screens = listOf(
        MainNavBarScreen.Home,
        MainNavBarScreen.Basket,
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        containerColor = BottomBarColor
    ) {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentRoute = currentRoute,
                navController = navController,
                basketViewModel = basketViewModel
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: MainNavBarScreen,
    currentRoute: String?,
    navController: NavHostController,
    basketViewModel: BasketViewModel // Injected via Hilt
) {
    val interactionSource = remember { MutableInteractionSource() }

    val itemCount by basketViewModel.basketItemCount.collectAsState()

    NavigationBarItem(
        icon = {
            Column(
                modifier = Modifier.clickableWithoutRipple(
                    interactionSource = interactionSource,
                    onClick = {
                        navController.navigate(screen.route) {
                            navController.graph.startDestinationRoute?.let { route ->
                                popUpTo(route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (screen is MainNavBarScreen.Basket) {
                    BadgedBox(
                        badge = {
                            if (itemCount > 0) {
                                Badge { Text( itemCount.toString()) }
                            }
                        }
                    ) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(id = screen.icon),
                            contentDescription = ""
                        )
                    }
                } else {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(id = screen.icon),
                        contentDescription = ""
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = screen.title,
                    fontSize = 12.sp,
                )
            }
        },
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = Dark900,
            selectedTextColor = Dark900,
            unselectedTextColor = Gray600,
            unselectedIconColor = Gray600,
            indicatorColor = Color.Transparent
        ),
        selected = currentRoute == screen.route,

        onClick = {
            navController.navigate(screen.route) {
                navController.graph.startDestinationRoute?.let { route ->
                    popUpTo(route) {
                        saveState = true
                    }
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    )
}

sealed class MainNavBarScreen(
    var route: String,
    var title: String,
    @DrawableRes val icon: Int,
) {
    data object Home : MainNavBarScreen(
        MainDestinations.HomeScreen_route,
        "Главная",
        icon = R.drawable.ic_home
    )
    data object Basket : MainNavBarScreen(
        MainDestinations.BasketScreen_route,
        "Корзина",
        icon = R.drawable.ic_basket
    )
}
