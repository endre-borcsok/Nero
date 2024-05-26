package com.ebsoftware.nero.feature.stocks.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.ebsoftware.nero.feature.stocks.StocksRoute

const val STOCKS_ROUTE = "stocks_route/"

fun NavController.navigateToStocks(
    navOptions: NavOptions,
) = navigate(STOCKS_ROUTE, navOptions)

fun NavGraphBuilder.stocksScreen(
    openSecurityMovement: (ticker: String) -> Unit,
) {
    composable(
        route = STOCKS_ROUTE,
    ) {
        StocksRoute(
            openSecurityMovement = openSecurityMovement,
        )
    }
}
