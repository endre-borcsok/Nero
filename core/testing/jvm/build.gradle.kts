plugins {
    alias(libs.plugins.nero.android.library)
}

android {
    namespace = "com.ebsoftware.nero.testing.jvm"
}

dependencies {
    testImplementation(libs.junit)
}