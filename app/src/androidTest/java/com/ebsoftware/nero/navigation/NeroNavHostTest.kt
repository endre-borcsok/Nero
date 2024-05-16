package com.ebsoftware.nero.navigation

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.ebsoftware.nero.MainActivity
import org.junit.Rule
import org.junit.Test

class NeroNavHostTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testStartDestinationIsStocksScreen() {
        composeRule.onNodeWithTag("stocks_route/")
            .assertExists()
    }
}
