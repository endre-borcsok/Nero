package com.ebsoftware.convention

import com.android.build.gradle.LibraryExtension
import com.ebsoftware.convention.common.configureAndroidTests
import com.ebsoftware.convention.common.configureGradleManagedDevices
import com.ebsoftware.convention.common.configureJvmTests
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("nero.android.library")
            }
            extensions.configure<LibraryExtension> {
                testOptions.animationsDisabled = true
                configureJvmTests(this)
                configureAndroidTests(this)
                configureGradleManagedDevices(this)
            }
        }
    }
}