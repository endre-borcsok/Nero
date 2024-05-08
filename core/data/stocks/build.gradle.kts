plugins {
    alias(libs.plugins.nero.android.library)
    alias(libs.plugins.nero.android.hilt)
}

android {
    namespace = "com.ebsoftware.nero.core.data.stocks"
}

dependencies {
    implementation(projects.core.database.stocks)
    implementation(projects.core.model.stocks)
}