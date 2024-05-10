package com.yoon.headspa.common.log

import com.fasterxml.jackson.databind.ObjectMapper
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import org.yaml.snakeyaml.util.UriEncoder
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Aspect
@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
class RequestLoggingAspect(
    @Value("\${spring.profiles.active}")
    val profile: String,
    private val objectMapper: ObjectMapper
) {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)
    private val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    @Pointcut("execution(* com.yoon.headspa..*Controller.*(..))")
    fun logRequest() { }

    @Around("logRequest()")
    fun doLogging(joinPoint: ProceedingJoinPoint): Any? {

        try {

            val request = ((RequestContextHolder.currentRequestAttributes()) as ServletRequestAttributes).request
            val originHeaders = mutableMapOf<String, String>()
            request.headerNames.toList().map { headerStr ->
                originHeaders[headerStr] = UriEncoder.decode(request.getHeader(headerStr))
            }

            val url = request.requestURI

            if(url == "/healthcheck") return joinPoint.proceed()

            if (profile == "stage" || profile == "prod") {
                val headersMap = mutableMapOf<String, Any>()

                headersMap["origin"] = originHeaders

                val paramsMap = mutableMapOf<String, Any>()
                request.parameterNames.toList().forEach { key ->
                    paramsMap[key] = UriEncoder.decode(request.getParameter(key))
                }

                println(
                    objectToJsonStr(
                        mutableMapOf (
                            "timestamp" to LocalDateTime.now().format(dateTimeFormatter),
                            "level" to "info",
                            "env" to if(profile == "stage") "stg" else profile,
                            "service" to "hotel-ota",
                            "url" to url,
                            "method" to request.method,
                            "function" to joinPoint.signature.name,
                            "headers" to headersMap,
                            "params" to paramsMap
                        )
                    )
                )
            }

        } catch (e: Exception) {
            log.error("로그 생성 도중 Exception이 발생 하였습니다. - {}", e.stackTrace)
        }

        return joinPoint.proceed()
    }

    private fun objectToJsonStr(any: Map<String, Any>): String {
        return objectMapper.writeValueAsString(any)
    }

}