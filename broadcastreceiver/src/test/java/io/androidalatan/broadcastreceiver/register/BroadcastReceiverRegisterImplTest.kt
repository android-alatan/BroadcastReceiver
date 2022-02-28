package io.androidalatan.broadcastreceiver.register

import android.content.BroadcastReceiver
import android.content.Context
import io.androidalatan.broadcastreceiver.register.api.BroadcastReceiverRegister
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class BroadcastReceiverRegisterImplTest {

    @Test
    fun `registerCallback and unregister`() {
        val context = mock<Context>()
        val register = BroadcastReceiverRegisterImpl(context)

        var count = 0
        val callback = BroadcastReceiverRegister.Callback {
            count++
        }
        register.registerCallback("a", "b", "c", callback = callback)

        val argumentCaptor = argumentCaptor<BroadcastReceiver>()
        verify(context).registerReceiver(argumentCaptor.capture(), any())
        val broadcastReceiver = argumentCaptor.firstValue

        Assertions.assertEquals(register.broadcastReceivers.size, 1)
        Assertions.assertTrue(register.broadcastReceivers.containsKey(callback))
        Assertions.assertEquals(register.broadcastReceivers[callback], broadcastReceiver)

        register.unregisterCallback(callback)
        verify(context).unregisterReceiver(argumentCaptor.capture())
        Assertions.assertEquals(broadcastReceiver, argumentCaptor.secondValue)
        Assertions.assertTrue(register.broadcastReceivers.isEmpty())

    }

}