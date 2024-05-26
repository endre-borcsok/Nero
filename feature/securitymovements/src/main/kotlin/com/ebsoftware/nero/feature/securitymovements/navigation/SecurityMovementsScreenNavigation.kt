package com.ebsoftware.nero.feature.securitymovements.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ebsoftware.nero.feature.securitymovements.SecurityMovementsRoute

internal const val ARG_TICKER_SYMBOL = "tickerSymbol"
const val SECURITY_MOVEMENTS_ROUTE = "securityMovements_route/{$ARG_TICKER_SYMBOL}"

fun NavController.navigateToSecurityMovements(
    tickerSymbol: String,
    navOptions: NavOptions? = null,
) = navigate(
    route = SECURITY_MOVEMENTS_ROUTE
        .replace("{$ARG_TICKER_SYMBOL}", tickerSymbol),
    navOptions = navOptions,
)

fun NavGraphBuilder.securityMovementsScreen() {
    composable(
        route = SECURITY_MOVEMENTS_ROUTE,
        arguments = listOf(
            navArgument(ARG_TICKER_SYMBOL) { type = NavType.StringType },
        ),
    ) {
        SecurityMovementsRoute()
    }
}
