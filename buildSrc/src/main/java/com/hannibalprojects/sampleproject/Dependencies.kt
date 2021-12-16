package com.hannibalprojects.sampleproject

object Dependencies {

    const val androidGradlePlugin = "com.android.tools.build:gradle:7.0.3"

    object Accompanist {
        private const val version = "0.19.0"
        const val swipeRefresh = "com.google.accompanist:accompanist-swiperefresh:$version"
    }

    object Squareup {
        private const val version = "2.9.0"
        const val retrofit = "com.squareup.retrofit2:retrofit:$version"
        const val retrofitGson = "com.squareup.retrofit2:converter-gson:$version"
        const val okhttp = "com.squareup.okhttp3:okhttp:4.9.1"
    }

    object Kotlin {
        private const val version = "1.5.31"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        const val extensions = "org.jetbrains.kotlin:kotlin-android-extensions:$version"
    }

    object Coroutines {
        private const val version = "1.4.1"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
    }

    object Room {
        private const val version = "2.4.0-alpha04"
        const val runtime = "androidx.room:room-runtime:$version"
        const val compiler = "androidx.room:room-compiler:$version"
        const val ktx = "androidx.room:room-ktx:$version"
    }

    object Hilt {
        private const val version = "2.38.1"
        const val android = "com.google.dagger:hilt-android:$version"
        const val compiler = "com.google.dagger:hilt-compiler:$version"
        const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        const val androidGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$version"
    }

    object AndroidX {
        const val coreKtx = "androidx.core:core-ktx:1.6.0-alpha03"

        const val appCompact = "androidx.appcompat:appcompat:1.3.1"

        object Compose {
            const val version = "1.0.5"

            const val foundation = "androidx.compose.foundation:foundation:$version"
            const val layout = "androidx.compose.foundation:foundation-layout:$version"
            const val ui = "androidx.compose.ui:ui:$version"
            const val uiUtil = "androidx.compose.ui:ui-util:$version"
            const val runtime = "androidx.compose.runtime:runtime:$version"
            const val material = "androidx.compose.material:material:$version"
            const val animation = "androidx.compose.animation:animation:$version"
            const val tooling = "androidx.compose.ui:ui-tooling:$version"
            const val iconsExtended = "androidx.compose.material:material-icons-extended:$version"
            const val iconsCore = "androidx.compose.material:material-icons-core:$version"
            const val uiTest = "androidx.compose.ui:ui-test-junit4:$version"
            const val liveData = "androidx.compose.runtime:runtime-livedata:$version"
            const val coilCompose = "io.coil-kt:coil-compose:1.4.0"
        }

        object Navigation {
            const val navigationCompose = "androidx.navigation:navigation-compose:2.4.0-beta02"
        }

        const val testArchCore = "androidx.arch.core:core-testing:2.1.0"
    }

    object JUnit {
        private const val version = "5.7.2"
        const val jupiterApi = "org.junit.jupiter:junit-jupiter-api:$version"
        const val jupiterEngine = "org.junit.jupiter:junit-jupiter-engine:$version"
        const val jupiterParams = "org.junit.jupiter:junit-jupiter-params:$version"
        const val vintageEngine = "org.junit.vintage:junit-vintage-engine:$version"
    }

    object Mockito {
        private const val version = "3.11.2"
        const val jupiterMockito = "org.mockito:mockito-junit-jupiter:$version"
        const val mockitoCore = "org.mockito:mockito-core:$version"
        const val mockitoInline = "org.mockito:mockito-inline:$version"
    }

    const val testMockWebServer = "com.squareup.okhttp3:mockwebserver:4.9.1"
    const val testAssert = "org.assertj:assertj-core:3.20.2"
}
