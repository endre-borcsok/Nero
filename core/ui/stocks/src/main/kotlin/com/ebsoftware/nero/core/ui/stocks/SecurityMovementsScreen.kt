package com.ebsoftware.nero.core.ui.stocks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ebsoftware.nero.core.ui.stocks.model.SecurityMovementGainViewData
import com.ebsoftware.nero.core.ui.stocks.model.SecurityMovementItem
import com.ebsoftware.nero.core.ui.stocks.model.SecurityMovementViewData

internal object SecurityMovementsScreenParameters {
    val contentPadding = 16.dp
    val columnSpacing = 8.dp
}

@Composable
fun SecurityMovementsScreen(
    securityMovements: List<SecurityMovementItem>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("com.ebsoftware.nero.core.ui.stocks.SecurityMovementsScreen_testTag"),
        contentPadding = PaddingValues(SecurityMovementsScreenParameters.contentPadding),
        verticalArrangement = Arrangement.spacedBy(SecurityMovementsScreenParameters.columnSpacing),
    ) {
        items(securityMovements) { viewData ->
            when (viewData) {
                is SecurityMovementViewData -> SecurityMovement(
                    viewData = viewData,
                )
                is SecurityMovementGainViewData -> SecurityMovementGain(
                    viewData = viewData,
                )
            }
        }
    }
}

@Preview
@Composable
internal fun SecurityMovementsScreenPreview() {
    SecurityMovementsScreen(
        securityMovements = List(5) {
            SecurityMovementViewData.EMPTY.copy(
                ticker = "AAPL",
                quantity = 4,
                cost = 123.445,
            )
        },
    )
}

@Preview
@Composable
internal fun SecurityMovementsGainScreenPreview() {
    SecurityMovementsScreen(
        securityMovements = List(5) {
            SecurityMovementGainViewData.EMPTY.copy(
                ticker = "AAPL",
                quantity = 4,
                cost = 123.445,
                gainPercent = -12.5,
                gainAmount = 1234.4,
            )
        },
    )
}
