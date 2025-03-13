// Top-level build file where you can add configuration options common to all sub-projects/modules.


buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        // Android Gradle Plugin
        classpath("com.android.tools.build:gradle:7.4.0") // Certifique-se de que está usando a versão compatível
        // Kotlin plugin
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.0") // Certifique-se de usar a versão compatível
        // Hilt plugin
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.48") // Adicione o plugin do Hilt aqui
    }
}


plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false

}