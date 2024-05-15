plugins {
    alias(libs.plugins.nero.android.library)
    alias(libs.plugins.nero.android.hilt)
}

android {
    namespace = "com.ebsoftware.nero.core.domain"
}

dependencies {
    implementation(projects.core.converter.stocks.hl)
    implementation(projects.core.model.stocks)
}
