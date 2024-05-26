package com.ebsoftware.nero.feature.securitymovements

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ebsoftware.nero.core.ui.base.ErrorScreen
import com.ebsoftware.nero.core.ui.base.LoadingScreen
import com.ebsoftware.nero.core.ui.stocks.SecurityMovementsScreen

@Composable
internal fun SecurityMovementsRoute(
    securityMovementsViewModel: SecurityMovementsViewModel = hiltViewModel(),
) {
    Screen(
        uiState = securityMovementsViewModel.uiState.collectAsStateWithLifecycle().value,
    )
}

@Composable
internal fun Screen(
    uiState: SecurityMovementsUiState,
    modifier: Modifier = Modifier,
) {
    when (uiState) {
        is SecurityMovementsUiState.Success -> SecurityMovementsScreen(
            securityMovements = uiState.securityMovements,
        )
        is SecurityMovementsUiState.Loading -> LoadingScreen(
            modifier = modifier,
        )
        is SecurityMovementsUiState.Error -> ErrorScreen(
            modifier = modifier,
            errorText = uiState.throwable.message.orEmpty(),
        )
    }
}
