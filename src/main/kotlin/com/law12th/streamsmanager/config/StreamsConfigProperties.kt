package com.law12th.streamsmanager.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.stereotype.Component

@Component
@Configuration
@PropertySource("classpath:config.streams.properties")
class StreamsConfigProperties {
    @Value("\${bootstrap.server}")
    var bootstrapServer: String = ""

    @Value("\${football.scraping.topic}")
    var inMatchStatsTopic: String = ""

    @Value("\${football.scraping.topic}")
    var inMatchBettingOddsTopic: String = ""

    @Value("\${football.scraping.topic}")
    var matchDayTopic: String = ""

    @Value("\${football.scraping.topic}")
    var preMatchStatsTopic: String = ""

    @Value("\${football.scraping.topic}")
    var preMatchBettingOddsTopic: String = ""

    @Value("\${football.scraping.topic}")
    var postMatchStatsTopic: String = ""

    @Value("\${consumer.application.id}")
    var consumerApplicationId: String = ""

    @Value("\${producer.application.id}")
    var producerApplicationId: String = ""
}