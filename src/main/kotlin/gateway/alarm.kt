package gateway

import coap.getClient
import mqtt.mqttClient
import org.eclipse.californium.core.CoapHandler
import org.eclipse.californium.core.CoapResource
import org.eclipse.californium.core.CoapResponse
import org.eclipse.californium.core.server.resources.CoapExchange
import org.eclipse.paho.mqttv5.common.MqttMessage
import translate.coapToGateway.alarmToAlarm as cgAlarmToAlarm

class Alarm : CoapResource("alarm") {
    init {
        attributes.title = name
    }

    override fun handleGET(exchange: CoapExchange?) {
        val alarm = String(getClient("alarm").get().payload)
        exchange?.respond(cgAlarmToAlarm(alarm))
    }

    override fun handlePOST(exchange: CoapExchange?) {
        val alarm = exchange?.requestText ?: return
    }
}
