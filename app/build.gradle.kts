plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.logitrack"
    compileSdk = 35  // Changed from 34 to 35

    defaultConfig {
        applicationId = "com.example.logitrack"
        minSdk = 24
        targetSdk = 34  // Keep targetSdk at 34 for stability
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
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

    viewBinding {
        enable = true
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:${libs.versions.appcompat.get()}")
    implementation("com.google.android.material:material:${libs.versions.material.get()}")
    implementation("androidx.activity:activity:${libs.versions.activity.get()}")
    implementation("androidx.constraintlayout:constraintlayout:${libs.versions.constraintlayout.get()}")
    testImplementation("junit:junit:${libs.versions.junit.get()}")
    androidTestImplementation("androidx.test.ext:junit:${libs.versions.junitVersion.get()}")
    androidTestImplementation("androidx.test.espresso:espresso-core:${libs.versions.espressoCore.get()}")

    // Room
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    annotationProcessor("androidx.room:room-compiler:2.6.1")  // Use annotationProcessor for Java

    // Lifecycle & Coroutines
    implementation("androidx.lifecycle:lifecycle-viewmodel:2.6.2")

    // Retrofit & OkHttp
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")

    // Material 3
    implementation("androidx.compose.material3:material3:1.2.1")

    // SwipeRefreshLayout
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

    // Tests
    testImplementation("junit:junit:4.13.2")
    testImplementation("androidx.room:room-testing:2.6.1")
    testImplementation("com.squareup.okhttp3:mockwebserver:4.11.0")
}
