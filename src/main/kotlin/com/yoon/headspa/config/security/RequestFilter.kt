package com.yoon.headspa.config.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.yoon.headspa.common.constant.AgentKeys.Companion.BUSINESS_NUMBER_KEY
import com.yoon.headspa.common.constant.AgentKeys.Companion.CALL_NUMBER_KEY
import com.yoon.headspa.common.constant.AgentKeys.Companion.COMPANY_ADDRESS_KEY
import com.yoon.headspa.common.constant.AgentKeys.Companion.COMPANY_NAME_KEY
import com.yoon.headspa.common.constant.AgentKeys.Companion.EMAIL_KEY
import com.yoon.headspa.common.constant.AgentKeys.Companion.FAX_NUMBER_KEY
import com.yoon.headspa.common.constant.AgentKeys.Companion.JWT_KEY
import com.yoon.headspa.common.constant.AgentKeys.Companion.LOGO_URL_KEY
import com.yoon.headspa.common.constant.AgentKeys.Companion.PHONE_NUMBER_KEY
import com.yoon.headspa.common.constant.AgentKeys.Companion.TRAVEL_AGENT_ID_KEY
import com.yoon.headspa.common.constant.AgentKeys.Companion.USER_ID_KEY
import com.yoon.headspa.config.utils.ResponseEntityUtils.Companion.getFailedResponseDto
import jakarta.annotation.PostConstruct
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class RequestFilter(
    private val objectMapper: ObjectMapper,
): OncePerRequestFilter() {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)
    private final lateinit var failureJson: String

    private val swaggerUris = arrayOf("/swagger-ui", "/v3/api-docs")
    private val lifeCycleCheckUri = "/healthcheck"
    private val cacheUri = "/cache"
    private val defaultHeadspaCommissionValue = 1.0

    @PostConstruct
    fun init() {
        failureJson = objectMapper.writeValueAsString(getFailedResponseDto().body)
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        val uriStr: String = request.requestURI?.toString() ?: ""

        println(uriStr)

//        if (isSwaggerUri(uriStr)
//            || uriStr == lifeCycleCheckUri
//            || uriStr.startsWith(cacheUri)
//        ) {
//            log.info("$uriStr : 페이지 접근 확인")
//            filterChain.doFilter(request, response)
//            return
//        }

        val userId = request.getHeader(USER_ID_KEY)
        val travelAgentId = request.getHeader(TRAVEL_AGENT_ID_KEY)
        val companyName = request.getHeader(COMPANY_NAME_KEY)
        val address = request.getHeader(COMPANY_ADDRESS_KEY)
        val businessNum = request.getHeader(BUSINESS_NUMBER_KEY)
        val callNum = request.getHeader(CALL_NUMBER_KEY)
        val fax = request.getHeader(FAX_NUMBER_KEY)
        val email = request.getHeader(EMAIL_KEY)
        val logo = request.getHeader(LOGO_URL_KEY)
        val phone = request.getHeader(PHONE_NUMBER_KEY)
        val jwtToken = request.getHeader(JWT_KEY)

//        if (
//            checkAgentInfoIsNull(userId, travelAgentId, companyName,
//            address, businessNum, callNum, fax, email, logo, phone)
//        || jwtToken.isEmpty()) {
//
//            val headers = mutableMapOf<String, String>()
//            request.headerNames.toList().map {
//                headers[it] = request.getHeader(it)
//            }
//
//            // TODO 필터를 어떻게 활용할지 고민
//            log.error("유저와 공급사 정보를 확인 할 수 없습니다. : url - {}, headers - {}", uriStr, headers)
//            setFailureResponse(response)
////            return
//        }

//        SecurityContextHolder.getContext().authentication =
//            UsernamePasswordAuthenticationToken(
////                TravelAgent(
////                    userId = userId,
////                    id = travelAgentId,
////                    name = UriEncoder.decode(companyName),
////                    address = UriEncoder.decode(address),
////                    businessNumber = businessNum,
////                    telNumber = callNum,
////                    faxNumber = fax,
////                    email = UriEncoder.decode(email),
////                    logoUrl = UriEncoder.decode(logo),
////                    mobileNumber = phone,
////                    jwtToken = jwtToken,
////                    eodingDiscountPercentage = getTravelAgentHotelEodingCommissionPercentage(travelAgentId)
////                ),
//                null,
//                emptyList()
//            )

        filterChain.doFilter(request, response)
    }

    private fun setFailureResponse(response: HttpServletResponse) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.writer.write(failureJson)
        response.status = HttpStatus.FORBIDDEN.value()
        response.contentType = MediaType.APPLICATION_JSON_VALUE
    }

    private fun isSwaggerUri(uriStr:String?): Boolean {
        if (uriStr == null) return false
        for (uri in swaggerUris)
            if (uriStr.startsWith(uri)) return true

        return false
    }

    private fun checkAgentInfoIsNull(vararg headers: String?): Boolean {
        return headers.map { it == null }.any { it }
    }

}