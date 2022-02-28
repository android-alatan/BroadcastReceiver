plugins {
    id("lib-kotlin-android-no-config")
    id("publish-android")
}

dependencies {
    api(project(":broadcastreceiver-api"))
}