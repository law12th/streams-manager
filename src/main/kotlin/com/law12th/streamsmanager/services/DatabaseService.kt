package com.law12th.streamsmanager.services

import com.law12th.streamsmanager.entities.data.Payload
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Service
import java.io.Serializable
import javax.sql.DataSource

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
class DatabaseService(
    @Autowired
    private val dataSource: DataSource
): Serializable, ApplicationListener<ApplicationReadyEvent> {

    override fun onApplicationEvent(event: ApplicationReadyEvent) {
    }

    fun getTopicName(payload: Payload): String? {
        var topic = topicCache[payload.payloadType]

        val sql = """
               SELECT 
                t.topic_name
               FROM ref.topic t
               WHERE t.payload_type = ?
        """.trimIndent()

        dataSource.connection.prepareStatement(sql).use { statement ->
            statement.setString(1, payload.payloadType)
            statement.executeQuery().use { resultSet ->
                while (resultSet.next()) {
                    topic = resultSet.getString(1)

                }
                if (topic !== null)
                    topicCache[payload.payloadType] = topic!!
            }
        }

        return topic
    }


    companion object {
        private val logger = LoggerFactory.getLogger(DatabaseService::class.java)
        private val topicCache = mutableMapOf<String, String>()
    }
}