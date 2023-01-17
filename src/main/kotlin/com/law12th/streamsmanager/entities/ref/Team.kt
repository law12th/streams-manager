package com.law12th.streamsmanager.entities.ref

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(schema = "ref", name = "team")
data class Team(
    @Id
    @Column(name = "id")
    var id: Int = 0,

    @Column(name = "full_name")
    var fullName: String = "",

    @Column(name = "short_name")
    var shortName: String = ""
)
