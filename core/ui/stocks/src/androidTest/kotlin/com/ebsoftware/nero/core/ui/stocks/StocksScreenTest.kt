package com.ebsoftware.nero.core.ui.stocks

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.longClick
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import com.ebsoftware.nero.core.ui.stocks.model.SecurityMovementViewData
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertTrue

class StocksScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun whenScreenItemsAreClickedThenTheyAreInvokingTheCorrectLambdas() {
        var addSecurityMovementsClicked = false
        var onEditSecurityMovementDetailsClicked = false

        composeRule.run {
            setContent {
                StocksScreen(
                    securityMovements = securityMovements,
                    onAddSecurityMovements = { addSecurityMovementsClicked = true },
                    onEditSecurityMovementDetails = { onEditSecurityMovementDetailsClicked = true },
                )
            }

            onNodeWithContentDescription(activity.resources.getString(R.string.stocks_screen_fab_content_description))
                .performClick()
            onNodeWithText("AAPL1")
                .performTouchInput { longClick() }
        }

        assertTrue(addSecurityMovementsClicked)
        assertTrue(onEditSecurityMovementDetailsClicked)
    }

    private val securityMovements =
        List(5) {
            SecurityMovementViewData.EMPTY.copy(
                ticker = "AAPL$it",
                quantity = 4,
                cost = 123.445,
            )
        }
}
