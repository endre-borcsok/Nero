package com.ebsoftware.nero.feature.stocks

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.ebsoftware.nero.core.ui.base.LoadingScreenParameters
import com.ebsoftware.nero.core.ui.stocks.StocksScreenParameters
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class StocksScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun whenInSuccessStateThenShowsStocksScreen() {
        composeRule.setContent {
            Screen(uiState = StocksUiState.Success(emptyList()))
        }

        composeRule.onNodeWithTag(StocksScreenParameters.TEST_TAG)
            .assertIsDisplayed()
    }

    @Test
    fun whenLaunchesActivityForResultThenUsesCorrectMimeType() {
        var mimeType = String()

        composeRule.setContent {
            Screen(
                uiState = StocksUiState.Success(emptyList()),
                onLaunchActivityForResult = { mimeType = it },
            )
        }

        composeRule.onNodeWithContentDescription(
            composeRule.activity.resources.getString(com.ebsoftware.nero.core.ui.stocks.R.string.stocks_screen_fab_content_description),
        ).performClick()

        assertEquals(
            expected = "*/*",
            actual = mimeType,
        )
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
