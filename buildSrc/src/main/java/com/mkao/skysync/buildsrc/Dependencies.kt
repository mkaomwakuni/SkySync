package com.mkao.skysync.buildsrc

object Configurations {
    const val CompileSdk = 34
    const val MinSdkVersion = 30
    const val TargetSdk = 35
    const val VersionCode = 1
    const val VersionName = "1.0"
}


object ClassPaths {
    const val gradlePlugin = "com.android.tools.build:gradle:8.1.2"
    const val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.10"
}

object Compose {
    const val composeVersion = "1.5.4"

    const val animation = "androidx.compose.animation:animation:$composeVersion"
    const val iconsExtended = "androidx.compose.material:material-icons-extended:$composeVersion"
    const val material = "androidx.compose.material:material:$composeVersion"
    const val runtime = "androidx.compose.runtime:runtime:$composeVersion"
    const val tooling = "androidx.compose.ui:ui-tooling:$composeVersion"
    const val ui = "androidx.compose.ui:ui:$composeVersion"
    const val uiUtil = "androidx.compose.ui:ui-util:$composeVersion"
    const val uiTest = "androidx.compose.ui:ui-test-junit4:$composeVersion"
    const val activityCompose = "androidx.activity:activity-compose:1.8.1"
    const val navigationCompose = "androidx.navigation:navigation-compose:2.8.4"
    const val hiltNavigation = "androidx.hilt:hilt-navigation-compose:1.1.0"
}

object Tests {
    private const val junitVersion = "4.13.2"
    private const val junitKtx = "1.1.5"

    const val junit = "junit:junit:$junitVersion"
    const val junitKotlin = "androidx.test.ext:junit-ktx:$junitKtx"
}

object Core {
    const val androidXCore = "androidx.core:core-ktx:1.12.0"
    const val appCompat = "androidx.appcompat:appcompat:1.6.1"
    const val material = "com.google.android.material:material:1.10.0"
}

object Libs {
    object Kotlin {
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.9.20"
    }

    object Coroutines {
        private const val version = "1.7.3"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
    }

    object DateTime {
        const val jodaTime = "net.danlew:android.joda:2.12.5"
    }

    object Lottie {
        const val lottie = "com.airbnb.android:lottie-compose:6.1.0"
    }

    object Accompanist {
        const val insets = "com.google.accompanist:accompanist-insets:0.32.0"
    }

    object GoogleLocation {
        const val location = "com.google.android.gms:play-services-location:21.0.1"
    }

    object Hilt {
        private const val version = "2.52"
        const val library = "com.google.dagger:hilt-android:$version"
        const val googleAndroidCompiler = "com.google.dagger:hilt-android-compiler:$version"
        const val googleCompiler = "com.google.dagger:hilt-compiler:$version"
        const val testing = "com.google.dagger:hilt-android-testing:$version"
        const val gradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$version"
    }
}