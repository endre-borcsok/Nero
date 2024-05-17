package com.ebsoftware.nero.core.ui.stocks

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.ebsoftware.nero.core.ui.stocks.model.SecurityMovementViewData
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class EditSecurityMovementDialogTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun testWhenDialogIsDisplayedThenTextIsCorrect() {
        composeRule.run {
            setContent {
                EditSecurityMovementDialog(
                    securityMovementViewData = SecurityMovementViewData.EMPTY.copy(
                        ticker = "AAPL",
                    ),
                    onUpdate = {},
                    onDismiss = {},
                )
            }

            onNodeWithText(
                activity.resources.getString(EditSecurityMovementDialogParameters.dialogTitle),
            ).isDisplayed()
            onNodeWithText(
                activity.resources.getString(EditSecurityMovementDialogParameters.dialogEditTicker),
            ).isDisplayed()
            onNodeWithText(
                text = "AAPL",
            ).isDisplayed()
        }
    }

    @Test
    fun testWhenTextIsEditedThenChangesAreReflectedInTheLambda() {
        composeRule.run {
            var updatedData: SecurityMovementViewData? = null

            setContent {
                EditSecurityMovementDialog(
                    securityMovementViewData = SecurityMovementViewData.EMPTY,
                    onUpdate = { updatedData = it },
                    onDismiss = {},
                )
            }

            onNodeWithText(
                activity.resources.getString(EditSecurityMovementDialogParameters.dialogEditTicker),
            ).performTextInput("IBM")
            onNodeWithText(
                activity.resources.getString(EditSecurityMovementDialogParameters.dialogConfirmText),
            ).performClick()

            assertEquals(
                expected = SecurityMovementViewData.EMPTY.copy(ticker = "IBM"),
                actual = updatedData,
            )
        }
    }

    @Test
    fun testWhenDismissedThenLambdaIsInvoked() {
        composeRule.run {
            var onDismiss = false

            setContent {
                EditSecurityMovementDialog(
                    securityMovementViewData = SecurityMovementViewData.EMPTY.copy(
                        ticker = "AAPL",
                    ),
                    onUpdate = {},
                    onDismiss = { onDismiss = true },
                )
            }

            onNodeWithText(
                activity.resources.getString(EditSecurityMovementDialogParameters.dialogCancelText),
            ).performClick()

            assertTrue(onDismiss)
        }
    }
}
