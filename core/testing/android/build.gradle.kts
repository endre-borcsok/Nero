plugins {
    alias(libs.plugins.nero.android.library)
    alias(libs.plugins.nero.android.hilt)
}

android {
    namespace = "com.ebsoftware.nero.testing.android"
}

dependencies {
    api(libs.androidx.junit)
    api(libs.kotlin.test)
    api(libs.kotlinx.coroutines.test)

    implementation(projects.core.data.stocks)
    implementation(projects.core.model.stocks)

    implementation(libs.androidx.test.runner)
    implementation(libs.hilt.android.testing)
}
