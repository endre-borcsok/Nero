plugins {
    alias(libs.plugins.nero.android.application)
}

android {
    namespace = "com.ebsoftware.nero"

    defaultConfig {
        applicationId = "com.ebsoftware.nero"
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    implementation(libs.androidx.core.ktx)
}