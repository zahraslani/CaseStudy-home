package gateway

import coap.getClient
import mqtt.mqttClient
import org.eclipse.californium.core.CoapHandler
import org.eclipse.californium.core.CoapResource
import org.eclipse.californium.core.CoapResponse
import org.eclipse.californium.core.server.resources.CoapExchange
import org.eclipse.paho.mqttv5.common.MqttMessage
import translate.coapToGateway.windowToWindow as cgWindowToWindow

class Window : CoapResource("window") {
    init {
        attributes.title = name
    }

    override fun handleGET(exchange: CoapExchange?) {
        val window = String(getClient("window").get().payload)
        exchange?.respond(cgWindowToWindow(window))
    }

    override fun handlePOST(exchange: CoapExchange?) {
        val window = exchange?.requestText ?: return
    }
}
