package com.yoon.headspa.common.controller

import com.yoon.headspa.common.rm.AnyRm
import com.yoon.headspa.config.utils.ResponseEntityUtils
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GeneralExceptionController {
    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    @ExceptionHandler(Exception::class)
    fun handlerException(
        e: Exception,
        request: HttpServletRequest,
        response: HttpServletResponse
    ): ResponseEntity<AnyRm> {
        log.error("Exception URL - {}", request.requestURI)
        log.error("Exception Cause - {}", e.cause?.message ?: "Not Found")
        log.error("Exception Message - {}", e.message)
        log.error("Exception StackTrace - {}", e.stackTrace)
        return ResponseEntityUtils.getFailedResponseDto().apply {
            this.body?.message = "요청 오류가 확인되었습니다."
        }
    }
}