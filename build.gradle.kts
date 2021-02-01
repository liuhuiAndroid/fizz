buildscript {
    val kotlinVersion by extra("1.4.0")
    repositories {
        google()
        maven("http://maven.aliyun.com/nexus/content/groups/public/")
        maven("http://maven.aliyun.com/nexus/content/repositories/jcenter")
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.1.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    }
}

allprojects {
    repositories {
        google()
        maven("http://maven.aliyun.com/nexus/content/groups/public/")
        maven("http://maven.aliyun.com/nexus/content/repositories/jcenter")
        maven("http://repo.baichuan-android.taobao.com/content/groups/BaichuanRepositories/")
        maven("https://jitpack.io")
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}