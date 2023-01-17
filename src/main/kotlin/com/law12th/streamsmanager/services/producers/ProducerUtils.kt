package com.law12th.streamsmanager.services.producers

import com.law12th.streamsmanager.config.StreamsConfigProperties
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringSerializer
import java.util.*

object ProducerUtils {
    fun producer(config: StreamsConfigProperties): KafkaProducer<String, String> {
        val producerProperties = Properties()
        producerProperties.setProperty(
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, config.bootstrapServer
        )
        producerProperties.setProperty(ProducerConfig.ACKS_CONFIG, "all")

        producerProperties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer::class.java.name)
        producerProperties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer::class.java.name)
        return KafkaProducer<String, String>(producerProperties)
    }

    fun sendMessage(topic: String, message: String, producer: KafkaProducer<String, String>) {
        val producerRecord = ProducerRecord<String, String>(topic, message)
        producer.send(producerRecord)
        producer.flush()
    }
}