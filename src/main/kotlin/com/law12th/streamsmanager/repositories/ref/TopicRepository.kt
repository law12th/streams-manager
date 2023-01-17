package com.law12th.streamsmanager.repositories.ref

import com.law12th.streamsmanager.entities.ref.Topic
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface TopicRepository : PagingAndSortingRepository<Topic, Int>
