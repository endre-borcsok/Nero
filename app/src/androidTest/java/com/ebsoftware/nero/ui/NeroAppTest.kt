package com.ebsoftware.nero.ui

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Rule
import org.junit.Test

class NeroAppTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testAppLaunch() {
        composeTestRule.setContent {
            NeroApp(
                appState = rememberNeroAppState(),
                modifier = Modifier.testTag("appTag"),
            )
        }
        composeTestRule.onNodeWithTag("appTag")
            .assertIsDisplayed()
    }
}
