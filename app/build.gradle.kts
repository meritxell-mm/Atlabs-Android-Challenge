plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    // id("org.jetbrains.kotlin.plugin.compose")
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
    /* buildFeatures {
         compose = true
     }
     composeCompiler {
         reportsDestination = layout.buildDirectory.dir("compose_compiler")
     }
     packaging {
         resources {
             excludes += "/META-INF/{AL2.0,LGPL2.1}"
         }
     }*/
}

dependencies {

    implementation(libs.kotlin.stdlib)
    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}