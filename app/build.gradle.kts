
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.kotlin.serialization)
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
   // id("org.jetbrains.kotlin.plugin.compose")
    alias(libs.plugins.compose.compiler)

}

android {
    namespace = "com.example.logointerpreterbeta"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.logointerpreterbeta"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunner = "com.example.logointerpreterbeta.CustomTestRunner"
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
        kotlinCompilerExtensionVersion = "1.5.13"
    }
    packaging {
        resources {
            excludes += "META-INF/*"
        }
    }
}

kapt {
    javacOptions {
        option("--add-exports", "jdk.compiler/com.sun.tools.javac.main=ALL-UNNAMED")
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
    implementation(files("lib/antlr-4.13.1-complete.jar"))
    implementation(libs.material3)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.colorpicker.compose)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.gson)
    implementation (libs.kotlinx.coroutines.android)
    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.navigation.testing)
    kapt(libs.hilt.android.compiler)

    //Zależności do testów Androidowych i UI:
    androidTestImplementation(libs.dagger.hilt.android.testing)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    androidTestImplementation(libs.mockk.mockk.android)
    androidTestImplementation(libs.androidx.core)
    androidTestImplementation(libs.androidx.runner)
    androidTestImplementation (libs.androidx.rules)
    androidTestImplementation (libs.androidx.navigation.testing) // do testowania nawigacji


    debugImplementation(libs.androidx.ui.tooling)
    testImplementation (libs.androidx.core.testing)


    //Zależności do testów jednostkowych:
    testImplementation(libs.dagger.hilt.android.testing)
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation (libs.androidx.navigation.compose) // Sprawdź aktualną wersję


    //Zależności do mockowania obiektów:
    testImplementation(libs.mockk)

    //Dodatkowe zależności debugujące:
    debugImplementation(libs.androidx.ui.test.manifest)

    //Zależności do używania Hilt:
    kaptAndroidTest(libs.hilt.compiler)
    kaptTest(libs.hilt.compiler)
//    kaptAndroidTest(libs.hilt.android.compiler.v2xx)
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}
