package com.law12th.streamsmanager.utilities

import com.fasterxml.jackson.databind.JsonNode

object ParserUtils {
    fun toJson(jsonStr: String): JsonNode? {
        return try {
            val mapper = ObjectMapperFactory.get()
            
            mapper.readTree(jsonStr)
        } catch (e: Exception) {
            return null
        }
    }
}