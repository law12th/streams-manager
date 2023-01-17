package com.law12th.streamsmanager.repositories.data

import com.law12th.streamsmanager.entities.data.Payload
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface PayloadRepository : PagingAndSortingRepository<Payload, Long> {
    @Query(
        nativeQuery = true,
        value = "SELECT * FROM data.payload p WHERE p.payload_type = :payload_type AND p.processed = :processed ORDER BY p.id LIMIT 50"
    )
    fun findByPayloadTypeAndProcessed(payloadType: String ,processed: Boolean): MutableIterable<Payload>
}
