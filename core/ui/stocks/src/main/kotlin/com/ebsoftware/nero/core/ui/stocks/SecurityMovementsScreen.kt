package com.ebsoftware.nero.core.ui.stocks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.ebsoftware.nero.core.ui.stocks.model.SecurityMovementViewData

internal object SecurityMovementsScreenParameters {
    val contentPadding = 16.dp
    val columnSpacing = 8.dp
}

@Composable
fun SecurityMovementsScreen(
    securityMovements: List<SecurityMovementViewData>,
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
            SecurityMovement(
                viewData = viewData,
            )
        }
    }
}

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
