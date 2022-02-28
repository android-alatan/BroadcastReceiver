package io.androidalatan.broadcastreceiver.register.adapter.flow

import io.androidalatan.broadcastreceiver.register.assertion.MockBroadcastReceiverRegister
import io.androidalatan.bundle.assertion.MapIntentData
import io.androidalatan.coroutine.test.turbine
import org.junit.jupiter.api.Assertions

import org.junit.jupiter.api.Test

class BroadcastReceiverRegisterFlowAdapterTest {

    @Test
    fun register() {
        val actions = arrayOf("a", "b", "c")
        val register = MockBroadcastReceiverRegister()
        register
            .register(*actions)
            .turbine { flowTurbine ->
                flowTurbine.expectNoEvents()

                val intentData = MapIntentData(mutableMapOf())
                register.trigger(actions, intentData)

                Assertions.assertEquals(flowTurbine.awaitItem(), intentData)

                flowTurbine.cancelAndIgnoreRemainingEvents()
            }

    }

    @Test
    fun registerForCount() {
        val actions = arrayOf("a", "b", "c")
        val register = MockBroadcastReceiverRegister()
        register
            .registerForCount(*actions)
            .turbine { flowTurbine ->
                flowTurbine.expectNoEvents()

                val intentData = MapIntentData(mutableMapOf())
                register.trigger(actions, intentData)

                Assertions.assertEquals(flowTurbine.awaitItem(), 1)

                flowTurbine.cancelAndIgnoreRemainingEvents()
            }

    }
}