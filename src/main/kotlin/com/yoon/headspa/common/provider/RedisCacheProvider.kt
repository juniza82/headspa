package com.yoon.headspa.common.provider

import com.yoon.headspa.common.constant.HotelStringConst
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.cache.CacheManager
import org.springframework.stereotype.Service

@Service
class RedisCacheProvider(
    private val cacheManager: CacheManager
) {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    fun clearAllRedisCaches(): Boolean {
        log.info("전체 캐시를 삭제합니다.")

        return try {
            cacheManager.cacheNames.forEach{
                log.info("Cache Name : {}", it)
                cacheManager.getCache(it)?.clear()
            }
            true
        } catch (e: Exception) {
            log.error("전체 캐시 삭제에 실패하였습니다. - {}", e.cause?.message)
            false
        }
    }

    fun clearExpediaHotelRedisCache(): Boolean {
        return try {
            log.info("{} 캐시 정보 삭제를 진행합니다.", HotelStringConst.HOTEL_AVAILABLE_CACHE_NAME)
            cacheManager.getCache(HotelStringConst.HOTEL_AVAILABLE_CACHE_NAME)?.let {
                it.clear()
                true
            } ?: false
        } catch (e: Exception) {
            log.error("캐시 정보 삭제에 실패했습니다. : name - {}", HotelStringConst.HOTEL_AVAILABLE_CACHE_NAME)
            log.error("Error Message : {}", e.stackTrace)
            false
        }
    }

}