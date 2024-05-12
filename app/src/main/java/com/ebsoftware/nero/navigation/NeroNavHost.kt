package com.ebsoftware.nero.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.ebsoftware.nero.feature.home.navigation.HOME_ROUTE
import com.ebsoftware.nero.feature.home.navigation.homeScreen
import com.ebsoftware.nero.ui.NeroAppState

@Composable
internal fun NeroNavHost(
    appState: NeroAppState,
    modifier: Modifier = Modifier,
    startDestination: String = HOME_ROUTE,
) {
    NavHost(
        navController = appState.navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        homeScreen()
    }
}
