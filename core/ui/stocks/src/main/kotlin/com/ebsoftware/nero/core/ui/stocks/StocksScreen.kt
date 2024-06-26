package com.ebsoftware.nero.core.ui.stocks

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ebsoftware.nero.core.ui.stocks.model.SecurityMovementViewData

internal object StocksScreenParameters {
    val contentPadding = 16.dp
    val columnSpacing = 8.dp
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StocksScreen(
    securityMovements: List<SecurityMovementViewData>,
    modifier: Modifier = Modifier,
    onAddSecurityMovements: () -> Unit,
    onSecurityMovementClicked: (SecurityMovementViewData) -> Unit,
    onEditSecurityMovementDetails: (SecurityMovementViewData) -> Unit,
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .testTag("com.ebsoftware.nero.core.ui.stocks.StocksScreen_testTag"),
        floatingActionButton = {
            SmallFloatingActionButton(
                onClick = onAddSecurityMovements,
            ) {
                Icon(Icons.Filled.Add, stringResource(id = R.string.stocks_screen_fab_content_description))
            }
        },
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding),
            contentPadding = PaddingValues(StocksScreenParameters.contentPadding),
            verticalArrangement = Arrangement.spacedBy(StocksScreenParameters.columnSpacing),
        ) {
            items(securityMovements) { viewData ->
                SecurityMovement(
                    viewData = viewData,
                    modifier = Modifier.combinedClickable(
                        onClick = { onSecurityMovementClicked(viewData) },
                        onLongClick = { onEditSecurityMovementDetails(viewData) },
                    ),
                )
            }
        }
    }
}

@Preview
@Composable
internal fun StocksScreenPreview() {
    StocksScreen(
        securityMovements = List(5) {
            SecurityMovementViewData.EMPTY.copy(
                ticker = "AAPL",
                quantity = 4,
                cost = 123.445,
            )
        },
        onAddSecurityMovements = {},
        onSecurityMovementClicked = {},
        onEditSecurityMovementDetails = {},
    )
}
