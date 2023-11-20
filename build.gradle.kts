// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    //id("com.android.application") version "8.1.1" apply false
    //id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.kapt) apply false
    alias(libs.plugins.hilt) apply false


    alias(libs.plugins.androidx.navigation.safeargs) apply false
}