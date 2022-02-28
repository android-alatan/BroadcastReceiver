plugins {
    id("lib-kotlin-android-no-config")
    id("publish-android")
}

android {
    testOptions {
        unitTests.isReturnDefaultValues = true
    }
}

dependencies {
    compileOnly(libs.common.androidAnnotation)
    api(libs.alantan.bundledata.api)
    api(project(":broadcastreceiver-api"))
    implementation(libs.alantan.bundledata.impl)
}