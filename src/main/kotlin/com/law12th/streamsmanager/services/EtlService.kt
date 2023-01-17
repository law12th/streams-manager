package com.law12th.streamsmanager.services

import com.law12th.streamsmanager.constants.Constants
import com.law12th.streamsmanager.entities.action.Match
import com.law12th.streamsmanager.entities.data.Payload
import com.law12th.streamsmanager.repositories.action.MatchRepository
import com.law12th.streamsmanager.repositories.data.PayloadRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class EtlService(
    @Autowired
    private val matchRepository: MatchRepository
) {

    fun process(payloads: List<Payload>) {
        val matchDayStatistics = payloads.filter { p -> p.payloadType == Constants.MD }
        val postMatchStatistics = payloads.filter { p -> p.payloadType == Constants.POMS }

        matchDayStatistics.forEach { ms ->
            val v = try {
//               addMatchDayStats(ms)
            } catch (e: Exception) {
                logger.error(e.message)
            }
        }
    }

    private fun addMatchDayStats(payload: Payload) {
//        return Match(
//            id = match.id,
//            countryId = match.countryId,
//            leagueId = match.leagueId,
//            season = match.season,
//            round = match.round,
//            matchDate = match.matchDate,
//            matchTime = match.matchTime,
//            homeTeamId = match.homeTeamId,
//            awayTeamId = match.awayTeamId
//        )
    }

    private fun parsePayloadData(payload: Payload) {

    }

    companion object {
        private val logger = LoggerFactory.getLogger(EtlService::class.java)
    }
}