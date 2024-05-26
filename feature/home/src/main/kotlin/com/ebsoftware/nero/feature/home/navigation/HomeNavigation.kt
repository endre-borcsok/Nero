package com.ebsoftware.nero.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.navigation
import com.ebsoftware.nero.feature.securitymovements.navigation.navigateToSecurityMovements
import com.ebsoftware.nero.feature.securitymovements.navigation.securityMovementsScreen
import com.ebsoftware.nero.feature.stocks.navigation.STOCKS_ROUTE
import com.ebsoftware.nero.feature.stocks.navigation.stocksScreen

const val HOME_ROUTE = "home_route/"

fun NavController.navigateToHome(
    navOptions: NavOptions,
) = navigate(HOME_ROUTE, navOptions)

fun NavGraphBuilder.homeNavigation(
    navController: NavController,
) {
    navigation(
        startDestination = STOCKS_ROUTE,
        route = HOME_ROUTE,
    ) {
        stocksScreen(
            openSecurityMovement = navController::navigateToSecurityMovements,
        )
        securityMovementsScreen()
    }
}
