plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

kapt {
    arguments {
        arg("AROUTER_MODULE_NAME", project.name)
    }
}

android {
    compileSdkVersion(project.compileSdk)

    defaultConfig {
        applicationId = "com.sec.fizz"
        minSdkVersion(project.minSdk)
        targetSdkVersion(project.targetSdk)
        versionCode = project.versionCode
        versionName = project.versionName
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
    kapt(Library.arouter_compiler)
    implementation(project(":common"))
    implementation(project(":news"))
}