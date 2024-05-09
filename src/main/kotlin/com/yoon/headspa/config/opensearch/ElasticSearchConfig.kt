package com.yoon.headspa.config.opensearch


import org.apache.http.HttpHost
import org.apache.http.auth.AuthScope
import org.apache.http.auth.UsernamePasswordCredentials
import org.apache.http.conn.ssl.NoopHostnameVerifier
import org.apache.http.impl.client.BasicCredentialsProvider
import org.apache.http.ssl.SSLContexts
import org.opensearch.client.RestClient
import org.opensearch.client.RestHighLevelClient
import org.opensearch.data.client.orhlc.AbstractOpenSearchConfiguration
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class ElasticSearchConfig (
    @Value("\${spring.elasticsearch.uris}")
    private val uri: String,
    @Value("\${spring.elasticsearch.username}")
    private val username: String,
    @Value("\${spring.elasticsearch.password}")
    private val password: String,
    @Value("\${spring.profiles.active}")
    val profile: String,
): AbstractOpenSearchConfiguration() {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    override fun opensearchClient(): RestHighLevelClient {

//        if (profile == "prod") { // TODO: 배포 환경에 맞춰 설정 정보 변경
            log.info("배포를 진행합니다.")
            val credentialsProvider = BasicCredentialsProvider()

            val host = HttpHost(
                uri.substring(0, uri.lastIndexOf(":")),
                uri.substring(uri.lastIndexOf(":") + 1).toInt(),
                "https")
            
            credentialsProvider.setCredentials(AuthScope(host), UsernamePasswordCredentials(username, password))

            val builder = RestClient.builder(host)
                .setHttpClientConfigCallback { httpClientBuilder ->
                    httpClientBuilder.setDefaultCredentialsProvider(
                        credentialsProvider
                    )
                    httpClientBuilder.setSSLContext(SSLContexts.custom().loadTrustMaterial(null) { _, _ -> true }.build())
                    httpClientBuilder.setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                }

            return RestHighLevelClient(builder)
//        }

//        log.info("개발 혹은 로컬 서버 배포를 진행합니다.")
//
//        val clientConfig =
//            ClientConfiguration
//                .builder()
//                .connectedTo(uri)
//                .usingSsl()
//                .withBasicAuth(username, password)
//                .build()
//
//        return RestClients.create(clientConfig).rest()
    }

}