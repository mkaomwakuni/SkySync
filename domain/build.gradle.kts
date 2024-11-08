import com.mkao.skysync.buildsrc.Compose
import com.mkao.skysync.buildsrc.Configurations
import com.mkao.skysync.buildsrc.Core
import com.mkao.skysync.buildsrc.Libs
import com.mkao.skysync.buildsrc.Tests

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = Configurations.CompileSdk

    defaultConfig {
        minSdk = Configurations.MinSdkVersion
        targetSdk = Configurations.TargetSdk


        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    packaging {
        resources {
            excludes.add("/META-INF/AL2.0")
            excludes.add("/META-INF/LGPL2.1")
        }
    }
    namespace = "com.mkao.skysync.domain"
}

dependencies {
    implementation(project(":domain"))
    implementation(Libs.Hilt.library)
    kapt(Libs.Hilt.googleAndroidCompiler)
    kapt(Libs.Hilt.googleCompiler)
    implementation(Libs.DateTime.jodaTime)
}