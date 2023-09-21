package gateway

import coap.getClient
import mqtt.mqttClient
import org.eclipse.californium.core.CoapHandler
import org.eclipse.californium.core.CoapResource
import org.eclipse.californium.core.CoapResponse
import org.eclipse.californium.core.server.resources.CoapExchange
import org.eclipse.paho.mqttv5.common.MqttMessage
import translate.coapToGateway.coolerToCooler as cgCoolerToCooler

class Cooler : CoapResource("cooler") {
    init {
        attributes.title = name
    }

    override fun handleGET(exchange: CoapExchange?) {
        val cooler = String(getClient("cooler").get().payload)
        exchange?.respond(cgCoolerToCooler(cooler))
    }

    override fun handlePOST(exchange: CoapExchange?) {
        val cooler = exchange?.requestText ?: return
    }
}
