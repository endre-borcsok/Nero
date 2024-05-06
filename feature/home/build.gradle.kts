plugins {
    alias(libs.plugins.nero.android.feature)
    alias(libs.plugins.nero.android.library.compose)
}

android {
    namespace = "com.ebsoftware.nero.feature.home"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.navigation.common.ktx)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.navigation.runtime.ktx)
}