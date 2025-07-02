plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.collegealerts"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.collegealerts"
        minSdk = 24
        targetSdk = 36
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    // AndroidX core essentials
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // Compose BOM (single source of truth)
    implementation(platform(libs.androidx.compose.bom))

    // Compose core UI
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.firebase.messaging)
    debugImplementation(libs.androidx.ui.tooling)

    // Material3
    implementation(libs.androidx.material3)
    implementation("androidx.compose.material3:material3:1.2.1")
    implementation(libs.androidx.ui.graphics)


    // Compose Navigation
    implementation (libs.androidx.navigation.navigation.compose)
    implementation(libs.androidx.navigation.runtime.android)
    implementation (libs.androidx.navigation.compose)


    // Lifecycle support in Compose (ViewModels, etc.)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.runtime.livedata)


    // Activity Compose support
    implementation (libs.androidx.activity.activity.compose)

    // Kotlin coroutines (optional but usually needed)
    implementation(libs.kotlinx.coroutines.android)

    // Activity support for Compose
    implementation ( libs.activity.compose)

    // Firebase Messaging
    implementation(libs.firebase.messaging.ktx)
    implementation(platform(libs.firebase.bom))

    // Tests
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation (libs.firebase.database.ktx)
    implementation (libs.firebase.auth.ktx) // If using auth
    implementation (libs.google.firebase.messaging.ktx) // For FCM

    implementation (libs.google.firebase.auth.ktx)
    implementation (libs.play.services.auth)


}







