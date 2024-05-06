plugins {
    alias(libs.plugins.nero.android.library)
    alias(libs.plugins.nero.android.hilt)
}

android {
    namespace = "com.ebsoftware.nero.core.stocks"
}

dependencies {
    implementation(projects.core.network.base)
    implementation(libs.retrofit.core)
}