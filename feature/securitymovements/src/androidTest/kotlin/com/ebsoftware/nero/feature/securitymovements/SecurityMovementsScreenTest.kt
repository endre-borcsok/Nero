package com.ebsoftware.nero.feature.securitymovements

import androidx.activity.ComponentActivity
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class SecurityMovementsScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun whenInSuccessStateThenShowsSecurityMovementsScreen() {
        composeRule.setContent {
            Screen(uiState = SecurityMovementsUiState.Success(emptyList()))
        }

        composeRule.onNodeWithTag("com.ebsoftware.nero.core.ui.stocks.SecurityMovementsScreen_testTag")
            .assertIsDisplayed()
    }

    @Test
    fun whenInLoadingStateThenShowsLoadingScreen() {
        composeRule.setContent {
            Screen(uiState = SecurityMovementsUiState.Loading)
        }

        composeRule.onNodeWithTag("com.ebsoftware.nero.core.ui.base_testTag")
            .assertIsDisplayed()
    }

    @Test
    fun whenInErrorStateThenShowsErrorScreen() {
        composeRule.setContent {
            Screen(uiState = SecurityMovementsUiState.Error(Exception("Test exception")))
        }

        composeRule.onNodeWithText("Test exception")
            .assertIsDisplayed()
    }
}
