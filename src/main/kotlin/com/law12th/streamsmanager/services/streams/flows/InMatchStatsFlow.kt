package com.law12th.streamsmanager.services.streams.flows

import com.law12th.streamsmanager.constants.Constants
import com.law12th.streamsmanager.entities.data.Payload
import com.law12th.streamsmanager.repositories.data.PayloadRepository
import com.law12th.streamsmanager.utilities.FlowUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withPermit
import kotlinx.coroutines.withContext
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Service
import java.io.Serializable

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
class InMatchStatsFlow(
    @Autowired
    private val payloadRepository: PayloadRepository
): Serializable, ApplicationListener<ApplicationReadyEvent> {
    override fun onApplicationEvent(event: ApplicationReadyEvent) {
        CoroutineScope(Dispatchers.Default).launch {
            startProcessingInMatchStats()
        }
    }

    private suspend fun startProcessingInMatchStats() {
       logger.info(">> Subscription on in match stats flow attached!")

       FlowUtils.inMatchStatsFlow.collect { ims ->
           payloadSemaphore.withPermit {
               if (ims.get("id") !== null) {
                   withContext(Dispatchers.IO) {
                       try {
                           payloadRepository.save(
                               Payload(
                                   payloadType = Constants.IMS,
                                   data = ims.asText()
                               )
                           )
                       } catch (e: Exception) {
                           logger.warn("Failed to process in match stats payload: ${e.message}")
                       }
                   }
               }
           }
       }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(InMatchStatsFlow::class.java)
        private val payloadSemaphore = Semaphore(10)
    }
}