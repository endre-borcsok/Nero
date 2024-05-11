package com.ebsoftware.nero.core.testing.screenshot

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.android.ide.common.rendering.api.SessionParams

fun paparazziScreenshotDevice(
    config: ScreenshotDeviceConfig = ScreenshotDeviceConfig.PHONE,
    renderingMode: SessionParams.RenderingMode = SessionParams.RenderingMode.SHRINK,
) = Paparazzi(
    deviceConfig = config.asPaparazziDeviceConfig(),
    renderingMode = renderingMode,
)

private fun ScreenshotDeviceConfig.asPaparazziDeviceConfig() =
    when (this) {
        ScreenshotDeviceConfig.PHONE -> DeviceConfig.PIXEL_6
    }