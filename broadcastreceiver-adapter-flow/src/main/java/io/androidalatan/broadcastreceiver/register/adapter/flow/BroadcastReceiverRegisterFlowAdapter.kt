package io.androidalatan.broadcastreceiver.register.adapter.flow

import io.androidalatan.broadcastreceiver.register.api.BroadcastReceiverRegister
import io.androidalatan.bundle.api.IntentData
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

fun BroadcastReceiverRegister.register(vararg actions: String): Flow<IntentData> {
    return callbackFlow {
        val callback = BroadcastReceiverRegister.Callback { intentData ->
            trySend(intentData)
        }
        registerCallback(*actions, callback = callback)

        awaitClose { unregisterCallback(callback) }
    }
}

fun BroadcastReceiverRegister.registerForCount(vararg actions: String): Flow<Long> {
    return callbackFlow {
        var count = 0L
        val callback = BroadcastReceiverRegister.Callback {
            trySend(++count)
        }
        registerCallback(*actions, callback = callback)

        awaitClose { unregisterCallback(callback) }
    }
}