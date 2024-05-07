plugins {
    alias(libs.plugins.nero.android.library)
}

android {
    namespace = "com.ebsoftware.nero.testing.android"
}

dependencies {
    api(libs.androidx.junit)
    api(libs.kotlin.test)
    api(libs.kotlinx.coroutines.test)

    implementation(libs.androidx.test.runner)
}