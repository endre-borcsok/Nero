package com.ebsoftware.convention

import com.android.build.gradle.LibraryExtension
import com.ebsoftware.convention.common.configureAndroidCompose
import com.ebsoftware.convention.common.configureAndroidTests
import com.ebsoftware.convention.common.configureGradleManagedDevices
import com.ebsoftware.convention.common.configureScreenshotTests
import com.ebsoftware.convention.common.configureJvmTests
import com.ebsoftware.convention.common.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("nero.android.library")
                apply("org.jmailen.kotlinter")
                apply("org.jetbrains.kotlin.plugin.compose")
            }
            extensions.configure<LibraryExtension> {
                testOptions.animationsDisabled = true
                configureJvmTests(this)
                configureAndroidTests(this)
                configureAndroidCompose(this)
                configureScreenshotTests(this)
                configureGradleManagedDevices(this)
            }
            dependencies {
                add("implementation", libs.findLibrary("androidx.core.ktx").get())
                add("implementation", libs.findLibrary("androidx.navigation.common.ktx").get())
                add("implementation", libs.findLibrary("androidx.navigation.compose").get())
                add("implementation", libs.findLibrary("androidx.navigation.runtime.ktx").get())
                add("implementation", libs.findLibrary("androidx.hilt.navigation.compose").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.runtime.compose").get())
            }
        }
    }
}
