package com.law12th.streamsmanager.entities.ref

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(schema = "ref", name = "country")
data class Country(
    @Id
    @Column(name = "id")
    var id: Int = 0,

    @Column(name = "country_name")
    var countryName: String = ""
)
