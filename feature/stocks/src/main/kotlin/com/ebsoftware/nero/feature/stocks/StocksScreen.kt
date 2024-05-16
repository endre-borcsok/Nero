package com.ebsoftware.nero.feature.stocks

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ebsoftware.nero.core.ui.base.ErrorScreen
import com.ebsoftware.nero.core.ui.base.LoadingScreen
import com.ebsoftware.nero.core.ui.stocks.StocksScreen
import com.ebsoftware.nero.feature.stocks.navigation.STOCKS_ROUTE

@Composable
internal fun StocksRoute(
    modifier: Modifier = Modifier,
    viewModel: StocksViewModel = hiltViewModel(),
) {
    val contentResolver = LocalContext.current.contentResolver
    val multiFilePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetMultipleContents(),
        onResult = { uris ->
            viewModel.addSecurityMovements(
                files = uris.mapNotNull(contentResolver::openInputStream),
            )
        },
    )

    Screen(
        uiState = viewModel.uiState.collectAsStateWithLifecycle().value,
        onLaunchActivityForResult = multiFilePicker::launch,
        modifier = modifier.testTag(STOCKS_ROUTE),
    )
}

@Composable
internal fun Screen(
    uiState: StocksUiState,
    modifier: Modifier = Modifier,
    onLaunchActivityForResult: (String) -> Unit = {},
) {
    when (uiState) {
        is StocksUiState.Success -> StocksScreen(
            onAddSecurityMovements = { onLaunchActivityForResult("*/*") },
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

@Preview
@Composable
internal fun ScreenPreview() {
    Screen(uiState = StocksUiState.Success(emptyList()))
}
