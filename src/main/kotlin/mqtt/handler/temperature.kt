package mqtt.handler

import coap.getClient
import org.eclipse.californium.core.CoapHandler
import org.eclipse.californium.core.CoapResponse
import org.eclipse.californium.core.coap.MediaTypeRegistry.TEXT_PLAIN
import translate.mqttToCoap.temperatureToCooler as mcTemperatureToCooler


import translate.mqttToCoap.temperatureToHeater as mcTemperatureToHeater


fun temperature(input: String) {
    getClient("cooler").post(object: CoapHandler {
        override fun onError() {
        }

        override fun onLoad(p0: CoapResponse?) {
        }
    }, mcTemperatureToCooler(input), TEXT_PLAIN)

    getClient("heater").post(object: CoapHandler {
        override fun onError() {
        }

        override fun onLoad(p0: CoapResponse?) {
        }
    }, mcTemperatureToHeater(input), TEXT_PLAIN)
}
