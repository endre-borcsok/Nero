package com.ebsoftware.nero.feature.stocks

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.longClick
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import com.ebsoftware.nero.core.ui.stocks.R
import com.ebsoftware.nero.core.ui.stocks.model.SecurityMovementViewData
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

        composeRule.onNodeWithTag("com.ebsoftware.nero.core.ui.stocks_testTag")
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
    fun whenSecurityMovementIsEditedThenDisplaysDialog() {
        var securityMovement: SecurityMovementViewData? = null

        composeRule.setContent {
            Screen(
                uiState = StocksUiState.Success(
                    listOf(
                        SecurityMovementViewData.EMPTY.copy(ticker = "AAPL"),
                    ),
                ),
                onEditSecurityMovementDetails = { securityMovement = it },
            )
        }

        composeRule.onNodeWithText("AAPL")
            .performTouchInput { longClick() }
        composeRule.onNodeWithText(
            composeRule.activity.resources.getString(R.string.security_movement_edit_dialog_title),
        ).assertIsDisplayed()
        composeRule.onNodeWithText(
            composeRule.activity.resources.getString(R.string.security_movement_edit_dialog_confirm),
        ).performClick()

        assertEquals(
            expected = SecurityMovementViewData.EMPTY.copy(ticker = "AAPL"),
            actual = securityMovement,
        )
    }

    @Test
    fun whenInLoadingStateThenShowsLoadingScreen() {
        composeRule.setContent {
            Screen(uiState = StocksUiState.Loading)
        }

        composeRule.onNodeWithTag("com.ebsoftware.nero.core.ui.base_testTag")
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
