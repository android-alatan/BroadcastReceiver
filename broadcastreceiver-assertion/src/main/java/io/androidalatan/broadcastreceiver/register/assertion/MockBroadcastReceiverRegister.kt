package io.androidalatan.broadcastreceiver.register.assertion

import io.androidalatan.broadcastreceiver.register.api.BroadcastReceiverRegister
import io.androidalatan.bundle.api.IntentData

class MockBroadcastReceiverRegister : BroadcastReceiverRegister {

    private val callbacks = mutableMapOf<BroadcastReceiverRegister.Callback, Array<String>>()

    fun trigger(actions: Array<String>, intentData: IntentData) {
        callbacks.filterValues { it.contentEquals(actions) }.keys.firstOrNull()
            ?.onIntent(intentData)
    }

    override fun registerCallback(vararg actions: String, callback: BroadcastReceiverRegister.Callback) {
        callbacks[callback] = arrayOf(*actions)
    }

    override fun unregisterCallback(callback: BroadcastReceiverRegister.Callback) {
        callbacks.remove(callback)

    }
}