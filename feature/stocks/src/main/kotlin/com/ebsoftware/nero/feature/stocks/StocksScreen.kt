package com.ebsoftware.nero.feature.stocks

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ebsoftware.nero.core.ui.base.ErrorScreen
import com.ebsoftware.nero.core.ui.base.LoadingScreen
import com.ebsoftware.nero.core.ui.stocks.StocksScreen

@Composable
internal fun StocksRoute(
    modifier: Modifier = Modifier,
    viewModel: StocksViewModel = hiltViewModel(),
) {
    Screen(
        modifier = modifier,
        uiState = viewModel.uiState.collectAsStateWithLifecycle().value,
    )
}

@Composable
internal fun Screen(
    uiState: StocksUiState,
    modifier: Modifier = Modifier,
) {
    when (uiState) {
        is StocksUiState.Success -> StocksScreen(
            onAddSecurityMovements = {},
            modifier = modifier,
        )
        is StocksUiState.Loading -> LoadingScreen(
            modifier = modifier,
        )
        is StocksUiState.Error -> ErrorScreen(
            modifier = modifier,
            errorText = uiState.throwable.message.orEmpty(),
        )
    }
}
