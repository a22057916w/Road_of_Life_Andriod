plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "com.bilab.lunsenluandroid"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.biolab.LunSenLu"
        minSdk = 29
        targetSdk = 36
        versionCode = 10012
        versionName = "1.0.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            setProguardFiles(listOf(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"))
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        compose = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

composeCompiler {
    reportsDestination = layout.buildDirectory.dir("compose_compiler")
    stabilityConfigurationFile = rootProject.layout.projectDirectory.file("stability_config.conf")
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("androidx.appcompat:appcompat:1.7.1")
    implementation("androidx.constraintlayout:constraintlayout:2.2.1")
    implementation("androidx.navigation:navigation-fragment:2.9.5")
    implementation("androidx.navigation:navigation-ui:2.9.5")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.9.4")
    implementation("androidx.activity:activity-compose:1.11.0")
    implementation(platform("androidx.compose:compose-bom:2025.10.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.lifecycle:lifecycle-viewmodel:2.9.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test:runner:1.7.0")
    androidTestImplementation("androidx.test.ext:junit:1.3.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.7.0")

    // gson、okhttp第三方套件
    implementation("com.squareup.okhttp3:okhttp:5.2.1")
    implementation("com.google.code.gson:gson:2.13.2")

    // Library nhisdk.aar、dexguard-runtime.aar
    // implementation(project(":dexguard-runtime"))
    // implementation(project(":nhisdk_beta1.0.4"))
    // implementation(project(":nhisdk_Beta1.0.2"))
    // implementation(project(":nhisdk_Beta1.07"))

    // https://mvnrepository.com/artifact/net.lingala.zip4j/zip4j
    // implementation("net.lingala.zip4j:zip4j:2.6.1")

    implementation("io.github.maitrungduc1410:AVLoadingIndicatorView:2.3.0")

    //circle imageview
    implementation("de.hdodenhof:circleimageview:3.1.0")
    //recyclerview
    implementation("androidx.recyclerview:recyclerview:1.4.0")

    /*//glidebumptech
    implementation("com.github.bumptech.glide:glide:4.11.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.11.0")*/

    implementation("com.android.volley:volley:1.2.1")
    implementation("androidx.security:security-crypto:1.1.0")

    // 3DWheelPicker (https://github.com/yijiebuyi/3DWheelPicker)
    implementation("com.github.yijiebuyi:3DWheelPicker:v1.2.0")

    // SnackBar
    implementation("com.google.android.material:material:1.13.0")

    // MyAndroidChart 圖表庫
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")

    // AnyChart for Box Chart (DNAmChartActivity)
    implementation("com.github.AnyChart:AnyChart-Android:1.1.5")

    androidTestImplementation(platform("androidx.compose:compose-bom:2025.10.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}
