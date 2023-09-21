package gateway

import coap.getClient
import mqtt.mqttClient
import org.eclipse.californium.core.CoapHandler
import org.eclipse.californium.core.CoapResource
import org.eclipse.californium.core.CoapResponse
import org.eclipse.californium.core.server.resources.CoapExchange
import org.eclipse.paho.mqttv5.common.MqttMessage
import translate.coapToGateway.lightToLight as cgLightToLight

import translate.gatewayToMqtt.lightToCurtain as gmLightToCurtain


import translate.gatewayToMqtt.lightToLamp as gmLightToLamp

import translate.gatewayToCoap.lightToLight as gcLightToLight

class Light : CoapResource("light") {
    init {
        attributes.title = name
    }

    override fun handleGET(exchange: CoapExchange?) {
        val light = String(getClient("light").get().payload)
        exchange?.respond(cgLightToLight(light))
    }

    override fun handlePOST(exchange: CoapExchange?) {
        val light = exchange?.requestText ?: return
        mqttClient.publish("curtain", MqttMessage(gmLightToCurtain(light).toByteArray()))

        mqttClient.publish("lamp", MqttMessage(gmLightToLamp(light).toByteArray()))
        getClient("light").post(object : CoapHandler {
            override fun onLoad(p0: CoapResponse?) {
            }

            override fun onError() {
            }
        }, gcLightToLight(light).toByteArray(), 0)
    }
}
