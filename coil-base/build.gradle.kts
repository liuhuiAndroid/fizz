plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
}

android {
    compileSdkVersion(project.compileSdk)

    defaultConfig {
        minSdkVersion(project.minSdk)
        targetSdkVersion(project.targetSdk)
    }

    buildTypes {
        getByName("release") {
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
}

dependencies {
    implementation(Library.kotlin_stdlib)
    implementation(Library.kotlin_coroutines_core)
    implementation(Library.kotlin_coroutines_android)
    implementation(Library.appcompat_resources)
    implementation(Library.core_ktx)
    implementation(Library.annotation)
    implementation(Library.collection_ktx)
    implementation(Library.exifinterface)
    implementation(Library.vectordrawable)
    implementation(Library.lifecycle_common)
    implementation(Library.lifecycle_runtime)
    implementation(Library.viewmodel_ktx)
    implementation(Library.livedata_ktx)
    implementation(Library.okhttp)
}