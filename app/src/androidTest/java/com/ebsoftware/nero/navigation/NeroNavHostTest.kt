package com.ebsoftware.nero.navigation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.ebsoftware.nero.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertTrue

@HiltAndroidTest
class NeroNavHostTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testStartDestinationIsStocksScreen() {
        composeRule.onNodeWithText("AAPL")
            .assertIsDisplayed()
        composeRule.onNodeWithText("IBM")
            .assertIsDisplayed()
    }

    @Test
    fun testSelectingStockNavigatesToSecurityMovementsScreen() {
        navigateToSecurityMovements("AAPL")
        assertTrue(
            composeRule.onAllNodesWithText("AAPL")
                .fetchSemanticsNodes().size == 2,
        )
    }

    private fun navigateToSecurityMovements(
        ticker: String,
    ) {
        composeRule.onNodeWithText(ticker)
            .performClick()
    }
}
