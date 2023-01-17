package com.law12th.streamsmanager.entities.ref

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(schema = "ref", name = "league")
data class League(
    @Id
    @Column(name = "id")
    var id: Int = 0,

    @Column(name = "league_name")
    var leagueName: String = ""
)
