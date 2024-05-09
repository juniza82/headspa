package com.yoon.headspa.test.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.yoon.headspa.common.provider.SlackProvider
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.batch.core.repository.JobRepository
import org.springframework.data.elasticsearch.core.ElasticsearchOperations
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/test")
class TestController(
    private val objectMapper: ObjectMapper,
    private val jobRepository: JobRepository,
    private val elasticsearchOperations: ElasticsearchOperations,
    private val slackProvider: SlackProvider,
) {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    @GetMapping("/error")
    fun errorLogTest() {
        log.error("TEST LOG")
//        slackProvider.expediaHotelReservationMessageSender()
//        slackProvider.expediaHotelReservationCancelMessageSender()
    }


}

