package com.ebsoftware.convention.common

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.api.attributes.java.TargetJvmEnvironment
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
        defaultConfig {
            testInstrumentationRunner = "com.ebsoftware.nero.testing.android.NeroTestRunner"
        }
        dependencies {
            add("androidTestImplementation", project(":core:testing:android"))
        }
    }
}

internal fun Project.configureScreenshotTests(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    plugins.withId("app.cash.paparazzi") {
        // Defer until afterEvaluate so that testImplementation is created by Android plugin.
        afterEvaluate {
            dependencies.constraints {
                add("testImplementation", "com.google.guava:guava") {
                    attributes {
                        attribute(
                            TargetJvmEnvironment.TARGET_JVM_ENVIRONMENT_ATTRIBUTE,
                            objects.named(TargetJvmEnvironment::class.java, TargetJvmEnvironment.STANDARD_JVM)
                        )
                    }
                    because("LayoutLib and sdk-common depend on Guava's -jre published variant." +
                            "See https://github.com/cashapp/paparazzi/issues/906.")
                }
            }
        }
    }
    commonExtension.apply {
        dependencies {
            add("testImplementation", project(":core:testing:screenshot"))
        }
    }
}
