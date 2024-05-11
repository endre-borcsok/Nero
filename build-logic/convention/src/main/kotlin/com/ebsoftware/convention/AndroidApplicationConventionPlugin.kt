package com.ebsoftware.convention

import com.android.build.api.dsl.ApplicationExtension
import com.ebsoftware.convention.common.configureAndroidTests
import com.ebsoftware.convention.common.configureGradleManagedDevices
import com.ebsoftware.convention.common.configureScreenshotTests
import com.ebsoftware.convention.common.configureJvmTests
import com.ebsoftware.convention.common.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("org.jmailen.kotlinter")
            }

            extensions.configure<ApplicationExtension> {
                defaultConfig.targetSdk = 34
                configureKotlinAndroid(this)
                configureJvmTests(this)
                configureAndroidTests(this)
                configureScreenshotTests(this)
                configureGradleManagedDevices(this)
            }
        }
    }
}
