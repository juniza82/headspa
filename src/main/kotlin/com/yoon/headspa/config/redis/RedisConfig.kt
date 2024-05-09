package com.yoon.headspa.config.redis

import org.springframework.beans.factory.annotation.Value
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import java.time.Duration

@EnableCaching
@EnableRedisRepositories
@Configuration
class RedisConfig(
    @Value("\${spring.data.redis.host}")
    val host: String,
    @Value("\${spring.data.redis.port}")
    val port: Int,
    @Value("\${spring.data.redis.database}")
    val database: Int
) {

    @Bean
    fun cacheManager(): CacheManager {
        val builder = RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(redisConnectionFactory())
        builder.cacheDefaults(defaultConfiguration())
        return builder.build()
    }

    fun defaultConfiguration(): RedisCacheConfiguration {
        return RedisCacheConfiguration.defaultCacheConfig()
            .serializeValuesWith(
                RedisSerializationContext.SerializationPair.fromSerializer(
                    GenericJackson2JsonRedisSerializer()
                )
            )
            .entryTtl(Duration.ofDays(1))
    }

    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory {
        return LettuceConnectionFactory(
            RedisStandaloneConfiguration(host, port).apply {
                this.database = database
            }
        )
    }

}