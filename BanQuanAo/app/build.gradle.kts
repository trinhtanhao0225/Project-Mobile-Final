plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.banquanao"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.banquanao"
        minSdk = 24
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)  // Sử dụng GSON converter

    // OkHttp (tùy chọn)
    implementation(libs.okhttp)

    // Glide (hiển thị ảnh)
    implementation("com.github.bumptech.glide:glide:4.15.1")
    annotationProcessor("com.github.bumptech.glide:compiler:4.15.1")

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // Volley (tùy chọn nếu dùng API khác)
    implementation("com.android.volley:volley:1.2.1")
}
