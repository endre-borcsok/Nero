package com.ebsoftware.nero.core.ui.stocks

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertTrue

class StocksScreenKtTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun whenScreenItemsAreClickedThenTheyAreInvokingTheCorrectLambdas() {
        var addSecurityMovementsClicked = false

        composeRule.run {
            setContent {
                StocksScreen(
                    onAddSecurityMovements = { addSecurityMovementsClicked = true },
                )
            }

            onNodeWithContentDescription(
                activity.resources.getString(R.string.stocks_screen_fab_content_description),
            ).performClick()
        }

        assertTrue(addSecurityMovementsClicked)
    }
}
