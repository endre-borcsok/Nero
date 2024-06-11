package com.ebsoftware.nero.core.ui.stocks

import com.ebsoftware.nero.core.testing.screenshot.ScreenshotDeviceConfig
import com.ebsoftware.nero.core.testing.screenshot.paparazziScreenshotDevice
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(TestParameterInjector::class)
class SecurityMovementsScreenTest(
    @TestParameter config: ScreenshotDeviceConfig,
) {
    @get:Rule
    val screenshotDevice =
        paparazziScreenshotDevice(
            config = config,
        )

    @Test
    fun testSecurityMovementsScreen() {
        screenshotDevice.snapshot {
            SecurityMovementsScreenPreview()
        }
    }

    @Test
    fun testSecurityMovementsGainsScreen() {
        screenshotDevice.snapshot {
            SecurityMovementsGainScreenPreview()
        }
    }
}
