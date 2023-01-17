package com.law12th.streamsmanager.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.stereotype.Component

@Component
@Configuration
@PropertySource("classpath:config.db.properties")
class DataSourceConfigProperties {
    @Value("\${password}")
    var password: String = ""

    @Value("\${username}")
    var username: String = ""

    @Value("\${serverPort}")
    var serverPort: String = ""

    @Value("\${databaseName}")
    var databaseName: String = ""

    @Value("\${serverName}")
    var serverName: String = ""
}