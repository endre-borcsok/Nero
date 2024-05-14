package com.ebsoftware.nero.feature.stocks

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.ebsoftware.nero.core.ui.base.LoadingScreenParameters
import org.junit.Rule
import org.junit.Test

class StocksScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun whenInSuccessStateThenShowsStocksScreen() {
        composeRule.setContent {
            Screen(uiState = StocksUiState.Success(emptyList()))
        }

        // TODO finish test
    }

    @Test
    fun whenInLoadingStateThenShowsLoadingScreen() {
        composeRule.setContent {
            Screen(uiState = StocksUiState.Loading)
        }

        composeRule.onNodeWithTag(LoadingScreenParameters.TEST_TAG)
            .assertIsDisplayed()
    }

    @Test
    fun whenInErrorStateThenShowsErrorScreen() {
        composeRule.setContent {
            Screen(uiState = StocksUiState.Error(Exception("Test exception")))
        }

        composeRule.onNodeWithText("Test exception")
            .assertIsDisplayed()
    }
}
