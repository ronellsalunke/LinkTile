plugins {
    id("com.android.application")
}

import java.util.Properties

val keystoreProperties = Properties().apply {
    rootProject.file("key.properties").inputStream().use { stream -> load(stream) }
}

android {
    namespace = "ronell.glancetiledemo"
    compileSdk = 36

    defaultConfig {
        applicationId = "ronell.glancetiledemo"
        minSdk = 30
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
    }

    signingConfigs {
        create("release") {
            storeFile = rootProject.file("key.jks")
            storePassword = keystoreProperties["storePassword"] as String
            keyAlias = keystoreProperties["keyAlias"] as String
            keyPassword = keystoreProperties["keyPassword"] as String
        }
    }
    buildTypes {
        release {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    packaging {
        dex {
            // AGP stores classes.dex uncompressed when minSdk >= 28
            useLegacyPackaging = true
        }
        resources {
            // Kotlin compiler metadata, only needed for reflection (unused).
            excludes += "kotlin/**"
            // Coroutines debug-agent metadata, not needed at runtime.
            excludes += "DebugProbesKt.bin"
            // Dependency license texts (~40 KB uncompressed).
            excludes += "META-INF/**/LICENSE.txt"
        }
    }
}

dependencies {
    implementation("androidx.wear.tiles:tiles:1.6.2")
    implementation("androidx.wear.protolayout:protolayout:1.4.2")
    implementation("androidx.concurrent:concurrent-futures:1.3.0")
}
