package com.ebsoftware.nero.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ebsoftware.nero.navigation.NeroNavHost

@Composable
internal fun NeroApp(
    appState: NeroAppState,
    modifier: Modifier = Modifier,
) {
    NeroNavHost(
        appState = appState,
        modifier = modifier,
    )
}