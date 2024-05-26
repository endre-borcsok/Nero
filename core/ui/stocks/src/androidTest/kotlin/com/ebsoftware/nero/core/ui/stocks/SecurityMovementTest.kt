package com.ebsoftware.nero.core.ui.stocks

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.longClick
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import com.ebsoftware.nero.core.ui.stocks.model.SecurityMovementViewData
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertTrue

class SecurityMovementTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun whenLongPressedThenInvokedEditDetailsLambda() {
        var onEditDetailsInvoked = false

        composeRule.setContent {
            SecurityMovement(
                viewData = SecurityMovementViewData.EMPTY.copy(
                    ticker = "testTicker",
                ),
                onClick = {},
                onEditDetails = { onEditDetailsInvoked = true },
            )
        }

        composeRule.onNodeWithText("testTicker")
            .performTouchInput { longClick() }

        assertTrue(onEditDetailsInvoked)
    }

    @Test
    fun whenClickedThenInvokedOnClickedLambda() {
        var onClickInvoked = false

        composeRule.setContent {
            SecurityMovement(
                viewData = SecurityMovementViewData.EMPTY.copy(
                    ticker = "testTicker",
                ),
                onClick = { onClickInvoked = true },
                onEditDetails = {},
            )
        }

        composeRule.onNodeWithText("testTicker")
            .performClick()

        assertTrue(onClickInvoked)
    }
}
