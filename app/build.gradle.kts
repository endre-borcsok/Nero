plugins {
    alias(libs.plugins.nero.android.application)
    alias(libs.plugins.nero.android.application.compose)
    alias(libs.plugins.nero.android.hilt)
}

android {
    namespace = "com.ebsoftware.nero"

    defaultConfig {
        applicationId = "com.ebsoftware.nero"
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(projects.feature.home)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.navigation.compose)
}
