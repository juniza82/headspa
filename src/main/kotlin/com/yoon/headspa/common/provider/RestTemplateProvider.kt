package com.yoon.headspa.common.provider

import com.yoon.headspa.config.resttemplate.RestTemplateConfig
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class RestTemplateProvider(
    restTemplateConfig: RestTemplateConfig
) {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    private final var restTemplate: RestTemplate = restTemplateConfig.restTemplate()

    fun <T> requestRestTemplateRequestWithReturnType(
        method: HttpMethod,
        url: String,
        body: Any?,
        headers: HttpHeaders?,
        returnType: Class<T>
    ): ResponseEntity<T> {
        log.info("요청 받은 요청 정보 : method - {}, url - {}, body - {}, header - {}, returnType - {}",
            method, url, body, headers, returnType)
        return when(method) {
            HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE ->
                request(url, method, body, headers, returnType)
            else -> throw Exception()
        }
    }

    fun requestRestTemplateRequestWithStringReturn(
        method: HttpMethod,
        url: String,
        body: Any?,
        headers: HttpHeaders?,
    ): ResponseEntity<String> {
        return requestRestTemplateRequestWithReturnType(
            method, url, body, headers, String::class.java
        )
    }

    private fun <T> request(
        url: String,
        method: HttpMethod,
        body: Any?,
        headers: HttpHeaders?,
        returnType: Class<T>
    ): ResponseEntity<T> {
        return restTemplate.exchange(
            url,
            method,
            HttpEntity(body, headers),
            returnType
        )
    }

}