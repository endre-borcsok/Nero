plugins {
    alias(libs.plugins.nero.android.library)
    alias(libs.plugins.nero.android.hilt)
    id("kotlinx-serialization")
}

android {
    namespace = "com.ebsoftware.nero.core.stocks"
}

dependencies {
    implementation(projects.core.network.base)

    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
}