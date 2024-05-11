plugins {
    alias(libs.plugins.nero.android.library)
}

android {
    namespace = "com.ebsoftware.nero.core.testing.screenshot"
}

dependencies {
    api(libs.test.parameter.injector)
    implementation(libs.paparazzi)
}