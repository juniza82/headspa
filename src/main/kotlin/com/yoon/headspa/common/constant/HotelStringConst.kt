package com.yoon.headspa.common.constant

// 호텔값 관련 이름 정보 모음, 캐쉬 이름 모음
class HotelStringConst {
    companion object {
        const val EXPEDIA_MAX_CHUNK_SIZE = 250
        const val NO_REFUND_STR = "취소 환불 불가"
        const val STAR_STR = "star"
        const val MEMBER_STR = "member"
        const val PARTNER_POINT_OF_SALE = "EAC_B2B_SA_PKG_MOD"
        const val PAYMENT_TERMS = "0"
        const val BILLING_TERMS = "EAC"

        // 캐시 정보 key 값 모음
        const val HOTEL_AVAILABLE_CACHE_NAME = "propertyDataList"
        const val HOTEL_AVAILABLE_HOTEL_CACHE_NAME = "propertyHotelDataList"
        const val HOTEL_STATIC_LIST_CACHE_NAME = "propertyStaticDataList"
        const val HOTEL_STATIC_CACHE_NAME = "propertyStaticData"
        const val HOTEL_STATIC_FILTER_CACHE_NAME = "propertyFilterData"
        const val HOTEL_STATIC_FRONT_FILTER_CACHE_NAME = "propertyFrontFilterData"
        const val HOTEL_RAW_PROPERTY_CACHE_NAME = "propertyRawPropertyDataList"
    }
}