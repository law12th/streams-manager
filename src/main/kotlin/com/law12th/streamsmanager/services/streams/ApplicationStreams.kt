package com.law12th.streamsmanager.services.streams

import org.apache.kafka.streams.KafkaStreams

interface ApplicationStreams {
    fun stream(): KafkaStreams
}