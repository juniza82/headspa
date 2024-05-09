package com.yoon.headspa.common.provider

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.nio.charset.Charset
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

@Component
class AESEncryptProvider(
    @Value("\${aes.key}")
    val keyStr: String,
    @Value("\${aes.iv}")
    val ivStr: String
) {

    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    private val algorithm = "AES/CBC/PKCS5Padding"
    private val algorithmName = "AES"

    fun useAESToEncrypt(plainText: String): String = try {
        Base64.getEncoder()
            .encodeToString(
                initCipher(Cipher.ENCRYPT_MODE)
                    .doFinal(plainText.toByteArray())
            )
    } catch (e: Exception) {
        log.error("암호화에 실패하였습니다. : {}", e.message)
        ""
    }

    fun useAESToDecrypt(encryptText: String): String = try {
        String(
            initCipher(Cipher.DECRYPT_MODE).doFinal(
                Base64.getDecoder()
                    .decode(encryptText)
            ),
            Charset.forName("UTF-8")
        )
    } catch (e: Exception) {
        log.error("복호화에 실패하였습니다. : {}", e.message)
        ""
    }

    fun useAESToDecryptReturnRequestText(encryptText: String): String = try {
        String(
            initCipher(Cipher.DECRYPT_MODE).doFinal(
                Base64.getDecoder()
                    .decode(encryptText)
            ),
            Charset.forName("UTF-8")
        )
    } catch (e: Exception) {
        log.error("복호화에 실패하였습니다. : {}", e.message)
        encryptText
    }

    private fun initCipher(cipherMode: Int) =
        Cipher.getInstance(algorithm).apply {
            init(
                cipherMode,
                SecretKeySpec(keyStr.toByteArray(), algorithmName),
                IvParameterSpec(ivStr.toByteArray())
            )
        }
}