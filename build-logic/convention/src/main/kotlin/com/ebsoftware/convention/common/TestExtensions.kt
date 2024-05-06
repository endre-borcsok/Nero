package com.ebsoftware.convention.common

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureJvmTests(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        dependencies {
            add("testImplementation", project(":core:testing:jvm"))
        }
    }
}

internal fun Project.configureAndroidTests(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        dependencies {
            add("androidTestImplementation", project(":core:testing:android"))
        }
    }
}