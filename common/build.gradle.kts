plugins {
    id("com.android.library")
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
    api(Library.kotlin_stdlib)
    api(Library.kotlin_coroutines_core)
    api(Library.kotlin_coroutines_android)
    api(Library.material)
    api(Library.appcompat)
    api(Library.activity_ktx)
    api(Library.fragment_ktx)
    api(Library.navigation_fragment_ktx)
    api(Library.navigation_ui_ktx)
    api(Library.viewmodel_ktx)
    api(Library.livedata_ktx)
    api(Library.work_ktx)
    api(Library.room_ktx)
    kapt(Library.room_compiler)
    api(Library.startup)
    api(Library.viewpager2)
    api(Library.constraintlayout)
    api(Library.recyclerview)
    api(Library.mmkv)
    api(Library.timber)
    api(Library.utdid)
    api(Library.lottie)
    api(Library.smartrefreshlayout)
    api(Library.smartrefreshlayout_header)
    api(Library.smartrefreshlayout_footer)
    api(Library.retrofit)
    api(Library.retrofit_moshi)
    api(Library.okhttp)
    api(Library.okhttp_logging_interceptor)
    api(Library.moshi)
    kapt(Library.moshi_kapt)
    api(Library.coil)
    api(Library.multidex)
    api(Library.arouter)
    kapt(Library.arouter_compiler)
    api(Library.picture_selector)
}