import gateway.*
import org.eclipse.californium.core.CoapServer
import org.eclipse.californium.core.network.CoapEndpoint
import org.eclipse.californium.elements.config.Configuration
import org.eclipse.californium.elements.util.NetworkInterfacesUtil
import java.net.InetSocketAddress

class GatewayServer : CoapServer() {
    init {
        val config = Configuration.getStandard()
        // Add an endpoint listener for each host network interface
        for (addr in NetworkInterfacesUtil.getNetworkInterfaces()) {
            val bindToAddress = InetSocketAddress(addr, 30456)
            val builder = CoapEndpoint.builder()
            builder.setInetSocketAddress(bindToAddress)
            builder.setConfiguration(config)
            addEndpoint(builder.build())
        }
        add(Cooler(), Heater(), Light(), Window(), Alarm(), Extinguisher())
    }
}
