package com.law12th.streamsmanager.utilities

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule

object ObjectMapperFactory {
    fun get(): ObjectMapper {
        val om = ObjectMapper()

        val module = SimpleModule()

        om.registerModule(module)

        return om
    }
}