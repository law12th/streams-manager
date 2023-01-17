package com.law12th.streamsmanager.entities.data

import com.fasterxml.jackson.databind.JsonNode
import org.hibernate.annotations.Type
import org.hibernate.annotations.TypeDef
import java.io.Serial
import java.io.Serializable
import java.time.LocalDate
import java.time.LocalTime
import javax.persistence.*

@Entity
@Table(schema = "data", name = "payload")
data class Payload(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = 0,

    @Column(name = "received_date")
    var receivedDate: LocalDate = LocalDate.now(),

    @Column(name = "received_time")
    var receivedTime: LocalTime = LocalTime.now(),

    @Column(name = "payload_type")
    var payloadType: String = "",

    @Column(name = "processed")
    var processed: Boolean = false,

    @Column(name = "data")
    var data: String = ""
): Serializable
