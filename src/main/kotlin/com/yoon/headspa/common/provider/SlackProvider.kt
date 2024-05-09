package com.yoon.headspa.common.provider

import jakarta.annotation.PostConstruct
//import com.yoon.headspa.common.model.TravelAgent
//import kr.co.eoding.hotelota.expedia.entity.jpa.EodingReservationEntity
import net.gpedro.integrations.slack.SlackApi
import net.gpedro.integrations.slack.SlackAttachment
import net.gpedro.integrations.slack.SlackMessage
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class SlackProvider(
    @Value("\${logging.slack.reservation-webhook-uri}")
    val hotelReservationWebhookUri: String,
    @Value("\${spring.profiles.active}")
    val profile: String,
) {

    private final lateinit var slackApi: SlackApi
    private final lateinit var slackExpediaReservationAttachment: SlackAttachment
    private final lateinit var slackExpediaReservationCancelAttachment: SlackAttachment
    private final lateinit var slackExpediaReservationMessage: SlackMessage

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    @PostConstruct
    fun init() {
        slackApi = SlackApi(hotelReservationWebhookUri)
        slackExpediaReservationAttachment = SlackAttachment().apply {
            this.setFallback("Ok")
            this.setColor("good")
            this.setTitle("[---${profile}---] Expedia Hotel 신규 예약 \uD83E\uDD29")
        }
        slackExpediaReservationCancelAttachment = SlackAttachment().apply {
            this.setFallback("Fail")
            this.setColor("danger")
            this.setTitle("[---${profile}---] Expedia Hotel 예약 취소 \uD83D\uDE2D")
        }
        slackExpediaReservationMessage = SlackMessage().apply {
            this.setText("예약 상세 정보")
        }
    }

    /*fun expediaHotelReservationMessageSender(
        travelAgent: TravelAgent,
        eodingReservationEntity: EodingReservationEntity
    ) {
        try {
            // prod/stage에서만 동작 하도록 함
            if (profile != "prod" && profile != "stage") return

            slackExpediaReservationAttachment.setText(
                "여행사 : ${travelAgent.name} \n예약코드 : ${eodingReservationEntity.code} \n호텔명 : ${eodingReservationEntity.hotelNameKo} \n판매금액 : \uD83D\uDCB0${eodingReservationEntity.amount?.toDouble()?.toInt()}\uD83D\uDCB0 \n예약상태 : ✅"
            )

            slackExpediaReservationMessage.setAttachments(
                listOf(slackExpediaReservationAttachment)
            )

            slackApi.call(slackExpediaReservationMessage)
        } catch (e: Exception) {
            log.error("예약 완료시 슬랙 전송에 실패했습니다. - {}", e.stackTrace)
        }
    }*/

    /*fun expediaHotelReservationCancelMessageSender(
        travelAgent: TravelAgent,
        eodingReservationEntity: EodingReservationEntity
    ) {
        try {
            // prod/stage에서만 동작 하도록 함
            if (profile != "prod" && profile != "stage") return

            slackExpediaReservationCancelAttachment.setText(
                "여행사 : ${travelAgent.name} \n예약코드 : ${eodingReservationEntity.code} \n호텔명 : ${eodingReservationEntity.hotelNameKo} \n판매금액 : \uD83D\uDCB0${eodingReservationEntity.amount?.toDouble()?.toInt()}\uD83D\uDCB0 \n예약상태 : ❌"
            )

            slackExpediaReservationMessage.setAttachments(
                listOf(slackExpediaReservationCancelAttachment)
            )

            slackApi.call(slackExpediaReservationMessage)

        } catch (e: Exception) {
            log.error("예약 취소시 슬랙 전송에 실패했습니다. - {}", e.stackTrace)
        }
    }*/

}