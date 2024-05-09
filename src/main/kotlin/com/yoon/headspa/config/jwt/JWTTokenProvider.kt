package com.yoon.headspa.config.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.SecretKey


@Component
class JWTTokenProvider (
    @Value("\${jwt.secret-key}")
    private val secretKey: String,
//    private val aesEncryptProvider: AESEncryptProvider
) {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    private val jwtTokenExpireDateLength = 180000 // 30분을 토큰의 유효 기간으로 설정함
    private var signKey: SecretKey? = null

    init {
        signKey = Keys.hmacShaKeyFor(
            Base64.getEncoder().encodeToString(secretKey.toByteArray()).toByteArray()
        )
    }

    private fun getJwtBody(token: String): Claims? {
        return try {
            Jwts.parserBuilder()
                .setSigningKey(signKey)
                .build()
                .parseClaimsJws(token).body
        } catch (e: Exception) {
            log.error("JWT body 읽는데 실패했습니다. - {}", e.stackTrace)
            null
        }
    }

    fun getSubjectFromToken(token: String): String {
        if (token.isEmpty()) {
            log.info("빈 JWT 토큰은 사용할 수 없습니다.")
            return ""
        }

        return try {
            getJwtBody(token)?.subject?.let {
//                aesEncryptProvider.useAESToDecrypt(it)
                it
            } ?: throw Exception()
        } catch (e: Exception) {
            log.error("Subject Data Decryption Failed : {}", e.message)
            ""
        }

    }

    fun getExpirationDateFromToken(token: String): Date {
        if (token.isEmpty()) {
            log.info("빈 JWT 토큰은 사용할 수 없습니다.")
            return Date(100)
        }

        return try {
            getJwtBody(token)?.expiration ?: throw Exception()
        } catch (e: Exception) {
            log.error("잘못된 jwt 요청입니다. - {}", e.message)
            Date(100)
        }
    }

    fun isValidToken(token: String): Boolean {
        if (token.isEmpty()) {
            log.info("빈 JWT 토큰은 사용할 수 없습니다.")
            return false
        }

        return getSubjectFromToken(token).isNotEmpty()
                && getExpirationDateFromToken(token).after(Date())
    }

    // 발급할 일은 없지만 일단 유지
    fun generateJWTToken(subject: String): String {
        return generateJWTToken(
            subject,
            System.currentTimeMillis(),
            jwtTokenExpireDateLength)
    }

    fun generateJWTToken(subject: String, nowMilliseconds: Long, lifeCycleLength: Int): String {

        if (subject.isEmpty()) {
            log.info("JWT 토큰 생성시 빈 String은 사용할 수 없습니다.")
            return ""
        }

        log.info("JWT 토큰 생성을 진행합니다.")
        // 공급사 정보 암호화가 필요하여 해당 데이터 암호화 진행
        val encryptedData = subject //aesEncryptProvider.useAESToEncrypt(subject)

        return if (encryptedData.isNotEmpty()) {
            log.info("암호화 데이터를 토큰화 진행합니다.")

            Jwts.builder()
                .setClaims(mapOf<String, Any>())
                .setSubject(encryptedData)
                .setIssuedAt(
                    Date(nowMilliseconds))
                .setExpiration(
                    Date(nowMilliseconds + lifeCycleLength))
                .signWith(signKey, SignatureAlgorithm.HS256)
                .compact()
        } else {
            log.info("암호화 실패로 토큰을 생성하지 못하였습니다.")
            ""
        }
    }

}