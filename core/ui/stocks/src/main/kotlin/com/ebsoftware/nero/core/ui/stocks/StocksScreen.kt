package com.ebsoftware.nero.core.ui.stocks

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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

class StocksScreenParameters {
    companion object {
        const val TEST_TAG = "com.ebsoftware.nero.core.ui.stocks_testTag"
    }
}

@Composable
fun StocksScreen(
    onAddSecurityMovements: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .testTag(StocksScreenParameters.TEST_TAG),
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
        ) {
        }
    }
}

@Preview
@Composable
internal fun StocksScreenPreview() {
    StocksScreen(
        onAddSecurityMovements = {},
    )
}
