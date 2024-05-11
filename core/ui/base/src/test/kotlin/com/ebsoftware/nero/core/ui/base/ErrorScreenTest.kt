package com.ebsoftware.nero.core.ui.base

import com.ebsoftware.nero.core.testing.screenshot.paparazziScreenshotDevice
import org.junit.Rule
import org.junit.Test

class ErrorScreenTest {

    @get:Rule
    val screenshotDevice = paparazziScreenshotDevice()

    @Test
    fun testErrorScreen() {
        screenshotDevice.snapshot {
            ErrorScreenPreview()
        }
    }
}