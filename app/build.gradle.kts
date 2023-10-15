plugins {
    id("com.android.application")
}

android {
    namespace = "rk.apenex.enamlottery"
    compileSdk = 33

    defaultConfig {
        applicationId = "rk.apenex.enamlottery"
        minSdk = 24
        targetSdk = 33
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //Country Picker Github
    implementation ("com.github.EhsanMashhadi:CountryPicker:0.4.0")

    //Bottom Navigation Menu Github
    implementation ("com.github.ibrahimsn98:SmoothBottomBar:1.7.9")
}