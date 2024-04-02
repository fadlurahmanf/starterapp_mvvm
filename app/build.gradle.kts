plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    kotlin("kapt") version "1.9.22"
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

android {
    namespace = "com.fadlurahmanf.starterappmvvm"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.fadlurahmanf.starterappmvvm"
        minSdk = 19
        targetSdk = 34
        versionCode = 1
        versionName = "0.0.1"
        multiDexEnabled = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            isDebuggable = true
        }
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

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        buildConfig = true
        viewBinding = true
    }

    flavorDimensions.add("environment")

    productFlavors {
        create("fake") {
            dimension = "environment"
            buildConfigField(
                "String",
                "BASE_MERCHANT_URL",
                "\"https://fake_merchant.bankmas.my.id\""
            )
            applicationIdSuffix = ".fake"
            resValue("string", "app_name", "Starter App MVVM Fake")
        }

        create("dev") {
            dimension = "environment"
            buildConfigField("String", "BASE_MERCHANT_URL", "\"https://merchant.bankmas.my.id\"")
            applicationIdSuffix = ".dev"
            resValue("string", "app_name", "Starter App MVVM Dev")
        }

        create("staging") {
            dimension = "environment"
            buildConfigField("String", "BASE_MERCHANT_URL", "\"https://merchant.bankmas.link\"")
            applicationIdSuffix = ".staging"
            resValue("string", "app_name", "Starter App MVVM Staging")
        }

        create("prod") {
            dimension = "environment"
            buildConfigField("String", "BASE_MERCHANT_URL", "\"https://merchant.bankmas.net\"")
            resValue("string", "app_name", "Starter App MVVM")
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.dagger)
    kapt(libs.dagger.compiler)

    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    implementation(libs.rxandroid)
    implementation(libs.rxjava)

    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)
    implementation(libs.google.firebase.analytics)

    implementation(libs.androidx.room.runtime)
    kapt(libs.androidx.room.compiler)
    implementation(libs.room.rxjava3)

    // ed25519
    implementation(libs.bcprov.jdk15on)

    implementation(libs.androidx.work.runtime.ktx)

    implementation(libs.glide)
    kapt(libs.compiler)
}