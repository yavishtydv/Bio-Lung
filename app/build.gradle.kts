plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.biolung.app"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.biolung.app"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

// Copies the debug APK to BioLungAndroid/BioLung.apk after ./gradlew assembleDebug
afterEvaluate {
    val exportApk = tasks.register<Copy>("exportApk") {
        from(tasks.named("packageDebug"))
        include("*.apk")
        into(rootProject.layout.projectDirectory)
        rename { "BioLung.apk" }
    }
    tasks.named("assembleDebug") { finalizedBy(exportApk) }
}
