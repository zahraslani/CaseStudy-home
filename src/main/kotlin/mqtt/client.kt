package mqtt

import org.eclipse.paho.mqttv5.client.MqttClient
import org.eclipse.paho.mqttv5.client.MqttConnectionOptions
import org.eclipse.paho.mqttv5.client.persist.MemoryPersistence

val mqttClient: MqttClient by lazy {
    val mqttUri = "tcp://localhost:1883"
    val clientId = "gateway"
    MqttClient(mqttUri, clientId, MemoryPersistence()).apply {
        val options = MqttConnectionOptions()
        options.connectionTimeout = 60
        options.keepAliveInterval = 60
        connect(options)
    }
}
