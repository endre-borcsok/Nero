package com.ebsoftware.convention

import com.android.build.api.variant.LibraryAndroidComponentsExtension
import com.android.build.gradle.LibraryExtension
import com.ebsoftware.convention.common.configureAndroidTests
import com.ebsoftware.convention.common.configureGradleManagedDevices
import com.ebsoftware.convention.common.configureScreenshotTests
import com.ebsoftware.convention.common.configureJvmTests
import com.ebsoftware.convention.common.configureKotlinAndroid
import com.ebsoftware.convention.common.disableUnnecessaryAndroidTests
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
                apply("org.jmailen.kotlinter")
            }

            extensions.configure<LibraryExtension> {
                defaultConfig.targetSdk = 34
                testOptions.animationsDisabled = true
                configureKotlinAndroid(this)
                configureJvmTests(this)
                configureAndroidTests(this)
                configureScreenshotTests(this)
                configureGradleManagedDevices(this)
            }
            extensions.configure<LibraryAndroidComponentsExtension> {
                disableUnnecessaryAndroidTests(target)
            }
        }
    }
}