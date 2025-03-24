import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.devtools.ksp)
}

android {
    namespace = "tech.atlabs.githubchallenge"
    compileSdk = 35

    buildFeatures {
        buildConfig = true
    }
    defaultConfig {
        applicationId = "tech.atlabs.githubchallenge"
        minSdk = 31
        targetSdk = 35
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        val properties = Properties()
        properties.load(project.rootProject.file("local.properties").reader())
        buildConfigField("String", "GITHUB_TOKEN", properties.getProperty("GITHUB_TOKEN"))
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
    // Material Design 3
    implementation(libs.material3)
    implementation(libs.androidx.material.icons.extended)
    // Optional - Integration with activities
    implementation(libs.activity.compose)
    // Optional - Integration with ViewModels
    implementation(libs.lifecycle.viewmodel.compose)
    implementation(libs.androidx.navigation.compose)

    // Android Studio Preview support
    implementation(libs.ui.tooling.preview)
    debugImplementation(libs.ui.tooling)

    //TESTING
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    // UI Tests //TODO?
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    testImplementation("io.mockk:mockk:1.13.2")

    //RETROFIT
    implementation(libs.retrofit)
    //MOSHI (JSON mapper)
    implementation(libs.moshi)
    implementation(libs.converter.moshi)
    implementation(libs.moshi.kotlin)
    ksp(libs.moshi.kotlin.codegen)

    //HILT
    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)
    ksp(libs.dagger.hilt.compiler)

    //COIL
    implementation(libs.coil.compose)

    //Status bar
    implementation(libs.accompanist.systemuicontroller)

}