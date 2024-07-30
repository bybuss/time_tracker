// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    extra.apply {
        set("compose_compiler_version", "1.5.2")
        set("lifecycle_version", "2.8.3")
        set("retrofit2_version", "2.9.0")
        set("hilt_version", "2.50")
        set("apollo_version", "3.8.2")
    }

    dependencies {
        classpath("com.apollographql.apollo3:apollo-gradle-plugin:3.8.2")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.50")
    }
}

plugins {
    id("com.android.application") version "8.5.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("com.google.dagger.hilt.android") version "2.50" apply false
}

