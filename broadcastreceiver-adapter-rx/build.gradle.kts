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

    api(libs.common.rxjava)
    api(project(":broadcastreceiver-api"))

    testImplementation(project(":broadcastreceiver-assertion"))
    testImplementation(libs.alantan.bundledata.assertion)
}