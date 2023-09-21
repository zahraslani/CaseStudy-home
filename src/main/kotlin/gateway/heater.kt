package gateway

import coap.getClient
import mqtt.mqttClient
import org.eclipse.californium.core.CoapHandler
import org.eclipse.californium.core.CoapResource
import org.eclipse.californium.core.CoapResponse
import org.eclipse.californium.core.server.resources.CoapExchange
import org.eclipse.paho.mqttv5.common.MqttMessage
import translate.coapToGateway.heaterToHeater as cgHeaterToHeater

class Heater : CoapResource("heater") {
    init {
        attributes.title = name
    }

    override fun handleGET(exchange: CoapExchange?) {
        val heater = String(getClient("heater").get().payload)
        exchange?.respond(cgHeaterToHeater(heater))
    }

    override fun handlePOST(exchange: CoapExchange?) {
        val heater = exchange?.requestText ?: return
    }
}
