package com.ebsoftware.convention.common

import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.ManagedVirtualDevice
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.invoke

internal fun configureGradleManagedDevices(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    val pixel6 = DeviceConfig("Pixel 6", 33, "aosp_atd", true)

    val allDevices = listOf(pixel6)
    val ciDevices = listOf(pixel6)

    commonExtension.testOptions {
        managedDevices {
            devices {
                allDevices.forEach { deviceConfig ->
                    maybeCreate(deviceConfig.taskName, ManagedVirtualDevice::class.java).apply {
                        device = deviceConfig.device
                        apiLevel = deviceConfig.apiLevel
                        systemImageSource = deviceConfig.systemImageSource
                        require64Bit = deviceConfig.require64Bit
                    }
                }
            }
            groups {
                maybeCreate("ci").apply {
                    ciDevices.forEach { deviceConfig ->
                        targetDevices.add(devices[deviceConfig.taskName])
                    }
                }
            }
        }
    }
}

private data class DeviceConfig(
    val device: String,
    val apiLevel: Int,
    val systemImageSource: String,
    val require64Bit: Boolean,
) {
    val taskName = buildString {
        append(device.lowercase().replace(" ", ""))
        append("api")
        append(apiLevel.toString())
        append(systemImageSource.replace("-", ""))
    }
}
