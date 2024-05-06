import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.ebsoftware.nero.buildlogic"

// Configure the build-logic plugins to target JDK 17
// This matches the JDK used to build the project, and is not related to what is running on device.
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "nero.android.application"
            implementationClass = "com.ebsoftware.convention.AndroidApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = "nero.android.library"
            implementationClass = "com.ebsoftware.convention.AndroidLibraryConventionPlugin"
        }
        register("androidFeature") {
            id = "nero.android.feature"
            implementationClass = "com.ebsoftware.convention.AndroidFeatureConventionPlugin"
        }
        register("androidApplicationCompose") {
            id = "nero.android.application.compose"
            implementationClass = "com.ebsoftware.convention.AndroidApplicationComposeConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "nero.android.library.compose"
            implementationClass = "com.ebsoftware.convention.AndroidLibraryComposeConventionPlugin"
        }
    }
}