plugins {
    alias(libs.plugins.nero.android.library)
    alias(libs.plugins.nero.android.library.compose)
}

android {
    namespace = "com.ebsoftware.nero.feature.home"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}