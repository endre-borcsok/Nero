plugins {
    alias(libs.plugins.nero.android.library)
    alias(libs.plugins.nero.android.hilt)
}

android {
    namespace = "com.ebsoftware.nero.core.network.base"
}

dependencies {
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)
}