

plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    antlr
    id("com.google.dagger.hilt.android") version "2.57.1" apply false
    alias(libs.plugins.compose.compiler) apply false

}

buildscript {
    dependencies {
        classpath (libs.hilt.android.gradle.plugin)
    }
}