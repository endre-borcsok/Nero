package com.ebsoftware.nero.ui

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.ebsoftware.nero.MainActivity
import org.junit.Rule
import org.junit.Test

class NeroAppTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testAppLaunch() {
        composeTestRule.onNodeWithTag("home_route/")
            .assertExists()
    }
}
