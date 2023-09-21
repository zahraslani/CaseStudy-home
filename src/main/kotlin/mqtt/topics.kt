package mqtt

import mqtt.handler.*
import org.eclipse.paho.mqttv5.client.IMqttToken
import org.eclipse.paho.mqttv5.client.MqttCallback
import org.eclipse.paho.mqttv5.client.MqttDisconnectResponse
import org.eclipse.paho.mqttv5.common.MqttException
import org.eclipse.paho.mqttv5.common.MqttMessage
import org.eclipse.paho.mqttv5.common.packet.MqttProperties

fun subscribe() {
    mqttClient.setCallback(object : MqttCallback {
        override fun disconnected(p0: MqttDisconnectResponse?) {
        }

        override fun mqttErrorOccurred(p0: MqttException?) {
        }

        override fun messageArrived(topic: String?, p1: MqttMessage?) {
            val message = String(p1?.payload ?: byteArrayOf())
            when (topic) {
                "temperature" -> temperature(message)

                "curtain" -> curtain(message)

                "lamp" -> lamp(message)

                "smoke" -> smoke(message)
            }
        }

        override fun deliveryComplete(p0: IMqttToken?) {
        }

        override fun connectComplete(p0: Boolean, p1: String?) {
        }

        override fun authPacketArrived(p0: Int, p1: MqttProperties?) {
        }

    })
    mqttClient.subscribe(arrayOf("temperature", "curtain", "lamp", "smoke"), intArrayOf(0, 0, 0, 0))
}
