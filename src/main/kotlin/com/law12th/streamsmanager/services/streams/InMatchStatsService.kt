package com.law12th.streamsmanager.services.streams

import com.law12th.streamsmanager.config.StreamsConfigProperties
import com.law12th.streamsmanager.services.StreamsConfigService
import com.law12th.streamsmanager.utilities.FlowUtils
import com.law12th.streamsmanager.utilities.ParserUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.KafkaStreams
import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.kstream.Consumed
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class InMatchStatsService(
    @Autowired
    private val streamsConfigProperties: StreamsConfigProperties,
    @Autowired
    private val streamsConfigService: StreamsConfigService
): ApplicationStreams {
    override fun stream(): KafkaStreams {
      val properties = streamsConfigService.streamConnectionProperties(streamsConfigProperties.consumerApplicationId)

      val builder = StreamsBuilder()

      builder.stream(streamsConfigProperties.inMatchStatsTopic,
       Consumed.with(Serdes.String(), Serdes.String())
      )
          .mapValues { json -> ParserUtils.toJson(json) }
          .foreach { _, payload ->
              CoroutineScope(Dispatchers.Default).launch {
                  FlowUtils.inMatchStatsFlow.emit(payload)
              }
          }
      
      val topology = builder.build()

      val streams = KafkaStreams(topology, properties)

      streams.cleanUp()

      return streams
    }

    companion object {
        private val logger = LoggerFactory.getLogger(InMatchStatsService::class.java)
    }
}