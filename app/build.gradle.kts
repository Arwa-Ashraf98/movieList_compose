import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    kotlin("kapt")
    id("kotlin-parcelize")
    alias(libs.plugins.hiltGradlePlugin)
}

android {
    namespace = "com.mad43.moviesapp"
    compileSdk = 34



    defaultConfig {
        applicationId = "com.mad43.moviesapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        val properties  = Properties()
        properties.load(project.rootProject.file("local.properties").inputStream())

        // Define buildConfigFields using values from local.properties
        buildConfigField("String", "API_KEY", "\"${properties.getProperty("API_KEY")}\"")
        buildConfigField("String", "BASE_URL", "\"${properties.getProperty("BASE_URL")}\"")
        buildConfigField("String", "BASE_IMAGE_URL", "\"${properties.getProperty("BASE_IMAGE_URL")}\"")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    buildFeatures {
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.androidCompiler)
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.navigation)
    kaptAndroidTest(libs.hilt.androidCompiler)
    androidTestImplementation(libs.hiltTesting)

    // coil
    implementation(libs.coilCompose)

    // retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofitConverter)
    implementation(libs.retrofitSerialization)
    implementation(libs.chucker.debugImplementation)
    implementation(libs.okHttp)
    implementation(libs.gson)
    implementation(libs.okHttpInterceptor)
    implementation(libs.kotlin.serialization)

    // room
    implementation(libs.room)
    kapt(libs.room.compiler)
    implementation(libs.room.ktx)
    implementation(libs.room.paging)

    // testing
    implementation(libs.kotlin.test)
    implementation(libs.jUnit.testing)

    // coroutine core
    implementation(libs.coroutineCore)

    // paging
    implementation(libs.paging)
    implementation(libs.paging.compose)

    // viewmodel
    implementation(libs.viewModel)
    implementation(libs.lifecycle)
    implementation(libs.lifecycle.compose)
    kapt(libs.lifecycle.compiler)

    // system ui controller
    implementation(libs.systemUiController)

    // extended icon
    implementation(libs.exteendedIcon)
}
