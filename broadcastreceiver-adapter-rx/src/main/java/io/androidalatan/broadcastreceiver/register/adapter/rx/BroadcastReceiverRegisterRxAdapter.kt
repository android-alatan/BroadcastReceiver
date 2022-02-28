package io.androidalatan.broadcastreceiver.register.adapter.rx

import io.androidalatan.broadcastreceiver.register.api.BroadcastReceiverRegister
import io.androidalatan.bundle.api.IntentData
import io.reactivex.rxjava3.core.Observable

fun BroadcastReceiverRegister.register(vararg actions: String): Observable<IntentData> {
    return Observable.create { emitter ->
        val callback = BroadcastReceiverRegister.Callback { intentData ->
            emitter.onNext(intentData)
        }
        registerCallback(*actions, callback = callback)

        emitter.setCancellable { unregisterCallback(callback) }
    }
}

fun BroadcastReceiverRegister.registerForCount(vararg actions: String): Observable<Long> {
    return Observable.create { emitter ->
        var count = 0L
        val callback = BroadcastReceiverRegister.Callback { intentData ->
            emitter.onNext(++count)
        }
        registerCallback(*actions, callback = callback)

        emitter.setCancellable { unregisterCallback(callback) }
    }
}