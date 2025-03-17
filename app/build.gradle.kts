plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    kotlin("kapt")
    id("com.google.dagger.hilt.android") // Certifique-se de que o plugin Hilt está presente
}

android {
    namespace = "com.pokemon_app"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.pokemon_app"
        minSdk = 30
        targetSdk = 35
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
        viewBinding = true
        dataBinding = true
    }
    kapt {
        correctErrorTypes = true
        useBuildCache = true
    }
}

dependencies {
    implementation ("com.github.bumptech.glide:glide:4.16.0")

    implementation("com.google.code.gson:gson:2.10.1")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")

    implementation("androidx.hilt:hilt-navigation-fragment:1.0.0")

    // Dependências do Hilt
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0") // Removido o hilt-navigation-fragment
    implementation("com.google.dagger:hilt-android:2.48")
    implementation(libs.androidx.room.common)
    implementation(libs.androidx.room.ktx)
    kapt("com.google.dagger:hilt-android-compiler:2.48")
    kapt("androidx.room:room-compiler:2.6.1") // ou a mesma versão que está usando nas outras libs Room

    //Compose ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")

    //Network call
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    //Json to kotlin object mapping
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    //image loading
    implementation("io.coil-kt:coil-compose:2.4.0")

    implementation ("androidx.appcompat:appcompat:1.6.1")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}