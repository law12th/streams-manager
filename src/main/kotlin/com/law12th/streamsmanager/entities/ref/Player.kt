package com.law12th.streamsmanager.entities.ref

import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(schema = "ref", name = "player")
data class Player(
    @Id
    @Column(name = "id")
    var id: Int = 0,

    @Column(name = "full_name")
    var fullName: String = "",

    @Column(name = "date_of_birth")
    var dateOfBirth: LocalDate = LocalDate.now()
)