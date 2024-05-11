plugins {
    alias(libs.plugins.nero.android.library)
}

android {
    namespace = "com.ebsoftware.nero.core.testing.screenshot"
}

dependencies {
    implementation(libs.paparazzi)
}