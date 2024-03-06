plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.fadlurahmanf.starterappmvvm"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.fadlurahmanf.starterappmvvm"
        minSdk = 19
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
        create("dev") {
            dimension = "environment"
            buildConfigField("String", "BASE_MERCHANT_URL", "\"https://merchant.bankmas.my.id\"")
        }

        create("staging") {
            dimension = "environment"
            buildConfigField("String", "BASE_MERCHANT_URL", "\"https://merchant.bankmas.link\"")
        }

        create("prod") {
            dimension = "environment"
            buildConfigField("String", "BASE_MERCHANT_URL", "\"https://merchant.bankmas.net\"")
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
    annotationProcessor(libs.dagger.compiler)

    implementation(libs.retrofit)
    implementation(libs.converter.gson)
}