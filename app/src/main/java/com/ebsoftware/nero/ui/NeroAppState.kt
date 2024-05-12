package com.ebsoftware.nero.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
internal fun rememberNeroAppState(
    navController: NavHostController = rememberNavController(),
) = remember {
    NeroAppState(
        navController = navController,
    )
}

internal class NeroAppState(
    val navController: NavHostController,
)
