package com.yoon.headspa.internal.dto
//
//import io.swagger.v3.oas.annotations.media.Schema
//import kr.co.eoding.hotelota.common.model.TravelAgent
//import kr.co.eoding.hotelota.common.utils.StringUtils
//
//class AgentInfoDto(
//    @Schema(description = "공급사 id") var id: Int = 0,
//    @Schema(description = "공급사 명") var name: String = "",
//    @Schema(description = "공급사 주소") var address: String = "",
//    @Schema(description = "공급사 사업자 번호") var businessNumber: String = "",
//    @Schema(description = "공급사 전화번호") var phone: String = "",
//    @Schema(description = "공급사 fax") var fax: String = "",
//    @Schema(description = "공급사 이메일") var email: String = "",
//    @Schema(description = "로고 이미지 url") var logo: String = "",
//) {
//    constructor(travelAgent: TravelAgent): this(
//        id = StringUtils.stringIntToInt(travelAgent.id),
//        name = travelAgent.name,
//        address = travelAgent.address,
//        businessNumber = travelAgent.businessNumber,
//        phone = travelAgent.mobileNumber,
//        fax = travelAgent.faxNumber,
//        email = travelAgent.email,
//        logo = travelAgent.logoUrl
//    )
//}