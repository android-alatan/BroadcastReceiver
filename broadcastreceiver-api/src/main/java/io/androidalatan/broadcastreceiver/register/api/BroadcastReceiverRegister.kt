package io.androidalatan.broadcastreceiver.register.api

import io.androidalatan.bundle.api.IntentData

interface BroadcastReceiverRegister {

    fun registerCallback(vararg actions: String, callback: Callback)
    fun unregisterCallback(callback: Callback)

    fun interface Callback {
        fun onIntent(intentData: IntentData)
    }

}