package com.law12th.streamsmanager.utilities

import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.coroutines.flow.MutableStateFlow

object FlowUtils {
    val inMatchStatsFlow = MutableStateFlow(ObjectMapper().readTree("{}"))
    val inMatchBettingFlow = MutableStateFlow(ObjectMapper().readTree("{}"))
    val preMatchStatsFlow = MutableStateFlow(ObjectMapper().readTree("{}"))
    val preMatchBettingFlow = MutableStateFlow(ObjectMapper().readTree("{}"))
    val matchDayFlow = MutableStateFlow(ObjectMapper().readTree("{}"))
    val PostMatchStatsFlow = MutableStateFlow(ObjectMapper().readTree("{}"))
}