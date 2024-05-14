package com.ebsoftware.nero.core.ui.base

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview

class LoadingScreen {
    companion object {
        const val TEST_TAG = "com.ebsoftware.nero.core.ui.base_testTag"
    }
}

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .testTag(LoadingScreen.TEST_TAG),
    ) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center),
        )
    }
}

@Preview
@Composable
internal fun LoadingScreenPreview() {
    LoadingScreen()
}
