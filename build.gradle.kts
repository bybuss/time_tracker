buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.apollographql.apollo3:apollo-gradle-plugin:3.8.2")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.50")
        classpath("com.google.android.libraries.mapsplatform.secrets-gradle-plugin:secrets-gradle-plugin:2.0.1")
    }
}

plugins {
    id("com.android.application") version "8.5.2" apply false
    id("com.android.library") version "8.5.2" apply false
    id("org.jetbrains.kotlin.android") version "2.0.0" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.0" apply false
    id("com.google.dagger.hilt.android") version "2.50" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "1.6.0" apply false
    id("com.google.devtools.ksp") version "2.0.0-1.0.21"
}