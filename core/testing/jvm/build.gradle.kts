plugins {
    alias(libs.plugins.nero.android.library)
}

android {
    namespace = "com.ebsoftware.nero.testing.jvm"
}

dependencies {
    api(libs.junit)
    api(libs.kotlinx.coroutines.test)
    api(libs.kotlin.test)
    api(libs.mockito.kotlin)
}