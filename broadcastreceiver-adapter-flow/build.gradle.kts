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

    api(libs.common.coroutine)
    api(project(":broadcastreceiver-api"))

    testImplementation(project(":broadcastreceiver-assertion"))
    testImplementation(project(":coroutine-test-util"))
    testImplementation(libs.alantan.bundledata.assertion)

}