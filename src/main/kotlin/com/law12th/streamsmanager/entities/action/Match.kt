package com.law12th.streamsmanager.entities.action

import java.io.Serializable
import java.time.LocalDate
import java.time.LocalTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(schema = "action", name = "match")
data class Match(
    @Id
    @Column(name = "id")
    var id: Long = 0,

    @Column(name = "country_id")
    var countryId: Int = 0,

    @Column(name = "league_id")
    var leagueId: Int = 0,

    @Column(name = "season")
    var season: String = "",

    @Column(name = "round")
    var round: Int = 0,

    @Column(name = "match_date")
    var matchDate: LocalDate = LocalDate.now(),

    @Column(name = "match_time")
    var matchTime: LocalTime = LocalTime.now(),

    @Column(name = "home_team_id")
    var homeTeamId: Int = 0,

    @Column(name = "away_team_id")
    var awayTeamId: Int = 0,

    @Column(name = "match_statistics")
    var matchStatistics: String = ""
): Serializable
