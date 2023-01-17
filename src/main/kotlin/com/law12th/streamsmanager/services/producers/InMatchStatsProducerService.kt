package com.law12th.streamsmanager.services.producers

import com.law12th.streamsmanager.config.StreamsConfigProperties
import com.law12th.streamsmanager.constants.Constants
import com.law12th.streamsmanager.repositories.data.PayloadRepository
import com.law12th.streamsmanager.services.DatabaseService
import org.apache.kafka.clients.producer.KafkaProducer
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.context.annotation.Scope
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.io.Serializable

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
class InMatchStatsProducerService(
    @Autowired
    private val payloadRepository: PayloadRepository,
    @Autowired
    private val config: StreamsConfigProperties,
    @Autowired
    private val databaseService: DatabaseService
): Serializable, ApplicationListener<ApplicationReadyEvent> {

    private lateinit var producer: KafkaProducer<String, String>

    override fun onApplicationEvent(event: ApplicationReadyEvent) {
        initProducer()
    }

    private fun initProducer() {
        producer = ProducerUtils.producer(config)
    }

    @Scheduled(cron = Constants.EveryAfterOneMinCronExpression, zone = Constants.Timezone)
    fun processInMatchStatsPayloads() {
       val payloads = payloadRepository.findByPayloadTypeAndProcessed(Constants.IMS, false)

       if (payloads.any()) {
           payloads.forEach{ payload ->
               try {
                   val topic = databaseService.getTopicName(payload)

                   if (topic !== null)
                       ProducerUtils.sendMessage(topic, payload.data, producer)
               } catch (e: Exception) {
                   logger.error("Error occurred match stats: ${e.message}")
               }
               payload.processed = true
               payloadRepository.save(payload)
           }
       }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(InMatchStatsProducerService::class.java)
    }
}