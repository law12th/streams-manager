package com.law12th.streamsmanager.repositories.action

import com.law12th.streamsmanager.entities.action.Match
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface MatchRepository : PagingAndSortingRepository<Match, Long>