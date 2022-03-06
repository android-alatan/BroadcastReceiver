[![Main Commit](https://github.com/android-alatan/BroadcastReceiver/actions/workflows/lib-main-branch.yml/badge.svg?branch=main)](https://github.com/android-alatan/BroadcastReceiver/actions/workflows/lib-main-branch.yml)
[![Release](https://jitpack.io/v/android-alatan/broadcastreceiver.svg)](https://jitpack.io/#android-alatan/broadcastreceiver)
# BroadcastReceiver

This is to make BroadcastReceiver of Android, register it and observe with RxJava/Coroutine Flow stream.

### Prerequisite
This repo has dependency to `android-alatan/BundleData`. In some cases, you may be required to add dependencies
```kotlin
implementation("com.github.android-alatan.BundleData:bundle-data:$version")
testImplementation("com.github.android-alatan.BundleData:bundle-data-assertion:$version")
```

### Installation
```kotlin
implementation("com.github.android-alatan.broadcastreceiver:broadcastreceiver:$version")
implementation("com.github.android-alatan.broadcastreceiver:broadcastreceiver-api:$version")
implementation("com.github.android-alatan.broadcastreceiver:broadcastreceiver-adapter-rx:$version")
implementation("com.github.android-alatan.broadcastreceiver:broadcastreceiver-adapter-flow:$version")

testImplementation("com.github.android-alatan.broadcastreceiver:broadcastreceiver-assertion:$version")
```

### Example
```kotlin
class FooActivity {
  fun onCreate() {
      FooViewModel(BroadcastReceiverRegisterImpl(this))
  }
}

class FooViewModel(broadcastReceiverRegister: BroadcastReceiverRegister) {
  init {
      broadcastReceiverRegister.register(Camera.ACTION_NEW_PICTURE)
          .subscribe { intentData -> 
              intentData.getUriStringOrNull() // new photo uri data 
          }
  }
}
```
by `broadcastreceiver-adapter-rx`, BroadcastReceiverRegister is listening to `Camera.ACTION_NEW_PICTURE` and get callback as RxJava/Coroutine Flow stream.

you can write JUnit test with `broadcastreceiver-assertion` 
```kotlin
class FooViewModelTest {
  private val broadcastRegister = MockBroadcastReceiverRegister()
  private val viewModel = FooViewModel(broadcastRegister)
    
  @Test
  fun test() {
    broadcastRegister.trigger(Camera.ACTION_NEW_PICTURE, MapIntentData(mutableMapOf(), uriString = "new-photo"))
    // verify else  
  }
}
```