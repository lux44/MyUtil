 plugins {
    //id("com.android.application")
    //id("org.jetbrains.kotlin.android")
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kapt)

    alias(libs.plugins.androidx.navigation.safeargs)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.example.myutil"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.myutil"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    }
    buildFeatures {
        viewBinding = true
        compose = true
        buildConfig = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("boolean", "RELEASE", "1")
        }
        debug {
            versionNameSuffix = "_001"
            isMinifyEnabled = true
            buildConfigField("boolean", "DEBUG", "0")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2"
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    implementation(libs.androidx.appcompat)

    implementation(libs.material)

    implementation(libs.constraintlayout)
    implementation(libs.constraintlayout.compose)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))

    implementation(libs.androidx.compose.ui)
    implementation(libs.graphics)
    implementation(libs.tooling)
    implementation(libs.tooling.preview)
    implementation(libs.test.junit4)
    implementation(libs.test.manifest)
    implementation(libs.viewbinding)

    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material)

    implementation(libs.androidx.compose.foundation)
    implementation(libs.foundation.layout)

    implementation(libs.androidx.compose.runtime)
    implementation(libs.runtime.livedata)

    implementation(libs.navigation.compose)
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui)

    implementation(libs.coil.compose)
    implementation(libs.coil.gif)

    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation(libs.hilt.android.gradle.plugin)
    implementation(libs.hilt.navigation.compose)

    kapt(libs.room.compiler)
    implementation(libs.room.ktx)
    implementation(libs.room.runtime)

    implementation(libs.datastore.preferences)
    implementation(libs.datastore.core)

    implementation(libs.work.testing)
    implementation(libs.work.runtime.ktx)

    implementation(libs.paging.compose)
    implementation(libs.paging.runtime.ktx)
    implementation(libs.tracing.ktx)

    implementation(libs.lifecycle.viewmodel.compose)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.lifecycle.livedata)
    implementation(libs.lifecycle.safestate)

    implementation(libs.glide)
    implementation(libs.glide.compose)
    implementation(libs.glide.transformation)

    implementation(libs.gson)
    implementation(libs.logging.interceptor)
    implementation(libs.retrofit2)
    implementation(libs.converter.gson)

    implementation(libs.profileinstaller)

    implementation(libs.accompanist.themeadapter.material)

    implementation(libs.timber)

    implementation(libs.serialization)
    //implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:2.0.0-Beta1")

    implementation(libs.lottie)
    implementation(libs.lottie.compose)

    implementation(libs.photoView)

    implementation(libs.dotsIndicator)

    implementation(libs.exoPlayer)

    implementation(libs.ffmpeg)

    implementation(libs.swipeRefeshLayout)

    //implementation ("com.github.CanHub:Android-Image-Cropper:4.0.0")

    //implementation(libs.org.jetbrains.kotlin.gradle.plugin)

}