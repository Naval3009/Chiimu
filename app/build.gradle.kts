plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("com.google.gms.google-services")
    id ("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.chiimu_minor_project_version_1"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.chiimu_minor_project_version_1"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
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

    //FireBase
    implementation(libs.firebase.auth)
    implementation (libs.play.services.auth)// implementation ("com.google.android.gms:play-services-auth:21.0.0")

    //HILT
    implementation(libs.hilt.android)//  implementation("com.google.dagger:hilt-android:2.49")
    kapt(libs.hilt.android.compiler)// kapt("com.google.dagger:hilt-android-compiler:2.49")
    implementation(libs.androidx.hilt.navigation.compose)//  implementation("androidx.hilt:hilt-navigation-compose:1.2.0")


    //NAVIGATION
    implementation(libs.androidx.lifecycle.viewmodel.compose)//    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
    implementation(libs.androidx.lifecycle.runtime.compose)//implementation("androidx.lifecycle:lifecycle-runtime-compose:2.7.0")
    implementation (libs.androidx.navigation.compose)//  implementation ("androidx.navigation:navigation-compose:2.7.7")
    //LOTTIE
    implementation(libs.lottie.compose)
    //pager
    implementation (libs.accompanist.pager)// for making horizontal swipe through pages




}