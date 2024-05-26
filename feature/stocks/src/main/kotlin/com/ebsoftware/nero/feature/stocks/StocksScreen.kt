package com.ebsoftware.nero.feature.stocks

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ebsoftware.nero.core.ui.base.ErrorScreen
import com.ebsoftware.nero.core.ui.base.LoadingScreen
import com.ebsoftware.nero.core.ui.stocks.EditSecurityMovementDialog
import com.ebsoftware.nero.core.ui.stocks.StocksScreen
import com.ebsoftware.nero.core.ui.stocks.model.SecurityMovementViewData
import com.ebsoftware.nero.feature.stocks.navigation.STOCKS_ROUTE

@Composable
internal fun StocksRoute(
    modifier: Modifier = Modifier,
    viewModel: StocksViewModel = hiltViewModel(),
    openSecurityMovement: (ticker: String) -> Unit,
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
        onSecurityMovementClicked = { openSecurityMovement(it.ticker) },
        onEditSecurityMovementDetails = viewModel::updateSecurityMovementsByAggregatedItem,
    )
}

@Composable
internal fun Screen(
    uiState: StocksUiState,
    modifier: Modifier = Modifier,
    onLaunchActivityForResult: (String) -> Unit = {},
    onSecurityMovementClicked: (SecurityMovementViewData) -> Unit = {},
    onEditSecurityMovementDetails: (SecurityMovementViewData) -> Unit = {},
) {
    val editedSecurityMovement = rememberSaveable { mutableStateOf<SecurityMovementViewData?>(null) }

    editedSecurityMovement.value?.let { it ->
        EditSecurityMovementDialog(
            securityMovementViewData = it,
            onDismiss = { editedSecurityMovement.value = null },
            onUpdate = {
                onEditSecurityMovementDetails(it)
                editedSecurityMovement.value = null
            },
        )
    }

    when (uiState) {
        is StocksUiState.Success -> StocksScreen(
            securityMovements = uiState.aggregatedSecurityMovements,
            onAddSecurityMovements = { onLaunchActivityForResult("*/*") },
            modifier = modifier,
            onSecurityMovementClicked = onSecurityMovementClicked,
            onEditSecurityMovementDetails = { editedSecurityMovement.value = it },
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
