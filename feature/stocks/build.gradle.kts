plugins {
    alias(libs.plugins.nero.android.feature)
    alias(libs.plugins.nero.android.hilt)
    alias(libs.plugins.nero.android.library.compose)
}

android {
    namespace = "com.ebsoftware.nero.feature.stocks"
}

dependencies {
    implementation(projects.core.data.stocks)
    implementation(projects.core.domain)
    implementation(projects.core.model.stocks)
    implementation(projects.core.ui.base)
    implementation(projects.core.ui.stocks)
}
