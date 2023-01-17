package com.law12th.streamsmanager.services

import com.law12th.streamsmanager.config.StreamsConfigProperties
import org.apache.kafka.streams.StreamsConfig
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.Properties

@Service
class StreamsConfigService(
    @Autowired
    private val config: StreamsConfigProperties
) {
    fun streamConnectionProperties(appId: String): Properties {
        val properties = Properties()

        properties.setProperty(
            StreamsConfig.APPLICATION_ID_CONFIG, appId
        )

        properties.setProperty(
            StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, config.bootstrapServer
        )

        return properties
    }
}