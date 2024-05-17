package com.ebsoftware.nero.core.ui.stocks

import com.ebsoftware.nero.core.testing.screenshot.ScreenshotDeviceConfig
import com.ebsoftware.nero.core.testing.screenshot.paparazziScreenshotDevice
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@Ignore("https://github.com/cashapp/paparazzi/issues/1354")
@RunWith(TestParameterInjector::class)
class EditSecurityMovementDialogTest(
    @TestParameter config: ScreenshotDeviceConfig,
) {

    @get:Rule
    val screenshotDevice =
        paparazziScreenshotDevice(
            config = config,
        )

    @Test
    fun testEditSecurityMovementDialog() {
        screenshotDevice.snapshot {
            EditSecurityMovementDialogPreview()
        }
    }
}
