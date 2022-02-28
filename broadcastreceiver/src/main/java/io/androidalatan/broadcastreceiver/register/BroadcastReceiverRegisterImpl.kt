package io.androidalatan.broadcastreceiver.register

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.annotation.VisibleForTesting
import io.androidalatan.broadcastreceiver.register.api.BroadcastReceiverRegister
import io.androidalatan.bundle.IntentDataImpl
import io.androidalatan.bundle.api.IntentData

class BroadcastReceiverRegisterImpl(private val context: Context) : BroadcastReceiverRegister {

    @VisibleForTesting
    internal val broadcastReceivers = mutableMapOf<BroadcastReceiverRegister.Callback, BroadcastReceiver>()

    override fun registerCallback(vararg actions: String, callback: BroadcastReceiverRegister.Callback) {
        val broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val intentData: IntentData = if (intent != null) {
                    IntentDataImpl(intent)
                } else {
                    IntentData.EMPTY
                }
                callback.onIntent(intentData)
            }
        }

        val intentFilter = IntentFilter().apply { actions.forEach { addAction(it) } }
        context.registerReceiver(broadcastReceiver, intentFilter)

        broadcastReceivers[callback] = broadcastReceiver
    }

    override fun unregisterCallback(callback: BroadcastReceiverRegister.Callback) {
        broadcastReceivers.remove(callback)
            ?.let { receiver ->
                context.unregisterReceiver(receiver)
            }

    }
}