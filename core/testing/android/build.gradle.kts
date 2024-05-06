plugins {
    alias(libs.plugins.nero.android.library)
}

android {
    namespace = "com.ebsoftware.nero.testing.android"
}

dependencies {
    api(libs.androidx.junit)
}