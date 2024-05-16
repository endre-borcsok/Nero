plugins {
    alias(libs.plugins.nero.android.feature)
    alias(libs.plugins.nero.android.library.compose)
    alias(libs.plugins.nero.android.hilt)
}

android {
    namespace = "com.ebsoftware.nero.feature.home"
}

dependencies {
    implementation(projects.feature.stocks)
}
