plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("org.jetbrains.kotlin.plugin.compose")
    // id("com.google.devtools.ksp")
    // id("dagger.hilt.android.plugin")
}

android {
    namespace = "tech.atlabs.githubchallenge"
    compileSdk = 35

    defaultConfig {
        applicationId = "tech.atlabs.githubchallenge"
        minSdk = 30
        targetSdk = 35
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
     packaging {
         resources {
             excludes += "/META-INF/{AL2.0,LGPL2.1}"
         }
     }
}

dependencies {

    implementation(libs.kotlin.stdlib)
    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.constraintlayout)

    //COMPOSE
    implementation(platform(libs.compose.bom))
    androidTestImplementation(platform(libs.compose.bom))
    // Material Design 3
    implementation(libs.material3)
    // Optional - Integration with activities
    implementation(libs.activity.compose)
    // Optional - Integration with ViewModels
    implementation(libs.lifecycle.viewmodel.compose)

    // Android Studio Preview support
    implementation(libs.ui.tooling.preview)
    debugImplementation(libs.ui.tooling)

    // UI Tests //TODO?
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    //TESTING
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}