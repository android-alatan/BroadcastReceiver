package io.androidalatan.broadcastreceiver.register.adapter.rx

import io.androidalatan.broadcastreceiver.register.assertion.MockBroadcastReceiverRegister
import io.androidalatan.bundle.assertion.MapIntentData

import org.junit.jupiter.api.Test

class BroadcastReceiverRegisterRxAdapterTest {

    @Test
    fun register() {
        val actions = arrayOf("a", "b", "c")
        val register = MockBroadcastReceiverRegister()
        val testObserver = register
            .register(*actions)
            .test()
            .assertNoErrors()
            .assertNoValues()
            .assertNotComplete()

        val intentData = MapIntentData(mutableMapOf())
        register.trigger(actions, intentData)

        testObserver.assertValueCount(1)
            .assertValue(intentData)
            .assertNoErrors()
            .assertNotComplete()
            .dispose()
    }

    @Test
    fun registerForCount() {
        val actions = arrayOf("a", "b", "c")
        val register = MockBroadcastReceiverRegister()
        val testObserver = register
            .registerForCount(*actions)
            .test()
            .assertNoErrors()
            .assertNoValues()
            .assertNotComplete()

        val intentData = MapIntentData(mutableMapOf())
        register.trigger(actions, intentData)

        testObserver.assertValueCount(1)
            .assertValue(1L)
            .assertNoErrors()
            .assertNotComplete()
            .dispose()

    }
}