package coap

import org.eclipse.californium.core.CoapClient

private val coapClientPool = mutableMapOf<String, CoapClient>()

fun getClient(name: String) = coapClientPool.getOrPut(name) {
    CoapClient("coap://localhost:5683/$name")
}
