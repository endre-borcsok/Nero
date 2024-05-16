plugins {
    alias(libs.plugins.nero.android.library)
    alias(libs.plugins.nero.android.library.compose)
}

android {
    namespace = "com.ebsoftware.nero.core.ui.stocks"
}

dependencies {
    implementation(projects.core.ui.stocks.model)
}
