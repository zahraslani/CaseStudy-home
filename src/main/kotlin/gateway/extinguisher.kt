package gateway

import coap.getClient
import mqtt.mqttClient
import org.eclipse.californium.core.CoapHandler
import org.eclipse.californium.core.CoapResource
import org.eclipse.californium.core.CoapResponse
import org.eclipse.californium.core.server.resources.CoapExchange
import org.eclipse.paho.mqttv5.common.MqttMessage
import translate.coapToGateway.extinguisherToExtinguisher as cgExtinguisherToExtinguisher

class Extinguisher : CoapResource("extinguisher") {
    init {
        attributes.title = name
    }

    override fun handleGET(exchange: CoapExchange?) {
        val extinguisher = String(getClient("extinguisher").get().payload)
        exchange?.respond(cgExtinguisherToExtinguisher(extinguisher))
    }

    override fun handlePOST(exchange: CoapExchange?) {
        val extinguisher = exchange?.requestText ?: return
    }
}
