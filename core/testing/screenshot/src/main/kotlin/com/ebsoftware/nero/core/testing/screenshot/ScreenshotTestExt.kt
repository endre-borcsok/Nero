package com.ebsoftware.nero.core.testing.screenshot

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.android.ide.common.rendering.api.SessionParams
import com.android.resources.NightMode

fun paparazziScreenshotDevice(
    config: ScreenshotDeviceConfig = ScreenshotDeviceConfig.PHONE_LIGHT,
    renderingMode: SessionParams.RenderingMode = SessionParams.RenderingMode.SHRINK,
) = Paparazzi(
    deviceConfig = config.asPaparazziDeviceConfig(),
    renderingMode = renderingMode,
)

private fun ScreenshotDeviceConfig.asPaparazziDeviceConfig() = when (this) {
    ScreenshotDeviceConfig.PHONE_LIGHT ->
        DeviceConfig.PIXEL_6.copy(
            nightMode = NightMode.NOTNIGHT,
        )
    ScreenshotDeviceConfig.PHONE_DARK ->
        DeviceConfig.PIXEL_6.copy(
            nightMode = NightMode.NIGHT,
        )
}
