plugins {
    alias(libs.plugins.nero.android.library)
    alias(libs.plugins.nero.android.hilt)
}

android {
    namespace = "com.ebsoftware.nero.core.converter.stocks.hl"
}

dependencies {
    implementation(projects.core.model.stocks)
    implementation(libs.opencsv)
}