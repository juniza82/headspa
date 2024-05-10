package com.yoon.headspa.internal.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.yoon.headspa.common.provider.SlackProvider
import com.yoon.headspa.intenal.entity.jpa.UsersEntity
import com.yoon.headspa.test.service.TestService
import io.swagger.v3.oas.annotations.Operation
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.batch.core.repository.JobRepository
import org.springframework.data.elasticsearch.core.ElasticsearchOperations
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/hello")
class HelloController(
    private val objectMapper: ObjectMapper,
    private val jobRepository: JobRepository,
    private val elasticsearchOperations: ElasticsearchOperations,
    private val slackProvider: SlackProvider,
    private val testService: TestService,
) {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    @GetMapping("/error")
    @Deprecated(message = "이건 스웨거에서 사용안할때 사용")
    @Operation(summary = "API 요약", description = "API 설명")
    fun errorLogTest(): List<UsersEntity> {
        log.info("TEST LOG ::::::: ")

        return testService.testSer()
    }


}

