package com.yoon.headspa.config.utils

import com.yoon.headspa.common.rm.AnyRm
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class ResponseEntityUtils {

    companion object {
        private val anyRm = AnyRm()
        fun getSuccessResponseDto(): ResponseEntity<AnyRm> = ResponseEntity(anyRm.apply {
            this.message = "Request Completed"
            this.code = "EODING"
        }, HttpStatus.OK)
        fun getFailedResponseDto(): ResponseEntity<AnyRm> = ResponseEntity(anyRm.apply {
            this.message = "Request Failed"
            this.code = "EODING"
        }, HttpStatus.INTERNAL_SERVER_ERROR)

        fun <T> getResponseDto(status: HttpStatus, message: String, code: String, body: T?): ResponseEntity<AnyRm> =
            ResponseEntity(anyRm.apply {
                this.message = message
                this.code = code
                this.data = body
            }, HttpStatus.OK)

        fun <T> getResponseDefaultDtoWithBody(body: T?): ResponseEntity<AnyRm> {
            val responseEntity = getSuccessResponseDto()
            responseEntity.body?.data = body

            return responseEntity
        }

        fun <T> getResponseEntityWithBody(body: T?): ResponseEntity<T> {
            return ResponseEntity(body, HttpStatus.OK)
        }

        fun <T> getResponseEntityByBodyAndStatusCode(body: T?, status: HttpStatus): ResponseEntity<T> {
            return ResponseEntity(body, status)
        }

    }

}

