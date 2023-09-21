package mqtt.handler

import coap.getClient
import org.eclipse.californium.core.CoapHandler
import org.eclipse.californium.core.CoapResponse
import org.eclipse.californium.core.coap.MediaTypeRegistry.TEXT_PLAIN
import translate.mqttToCoap.smokeToWindow as mcSmokeToWindow


import translate.mqttToCoap.smokeToAlarm as mcSmokeToAlarm


import translate.mqttToCoap.smokeToExtinguisher as mcSmokeToExtinguisher


fun smoke(input: String) {
    getClient("window").post(object: CoapHandler {
        override fun onError() {
        }

        override fun onLoad(p0: CoapResponse?) {
        }
    }, mcSmokeToWindow(input), TEXT_PLAIN)

    getClient("alarm").post(object: CoapHandler {
        override fun onError() {
        }

        override fun onLoad(p0: CoapResponse?) {
        }
    }, mcSmokeToAlarm(input), TEXT_PLAIN)

    getClient("extinguisher").post(object: CoapHandler {
        override fun onError() {
        }

        override fun onLoad(p0: CoapResponse?) {
        }
    }, mcSmokeToExtinguisher(input), TEXT_PLAIN)
}
