package com.yoon.headspa.config.datasource

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.apache.ibatis.session.SqlSessionFactory
import org.mybatis.spring.SqlSessionFactoryBean
import org.mybatis.spring.SqlSessionTemplate
import org.mybatis.spring.annotation.MapperScan
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.support.PathMatchingResourcePatternResolver
import org.springframework.jdbc.datasource.DriverManagerDataSource
import javax.sql.DataSource

@Suppress("unused")
@Configuration
//@MapperScan(basePackages = ["com.yoon.headspa.*.repository.mapper"])
@MapperScan(basePackages = ["com.yoon.headspa.*"])
class DataSourceConfig(
    @Value("\${spring.datasource.driver-class-name}")
    val driverClassName: String,
    @Value("\${spring.datasource.url}")
    val url: String,
    @Value("\${spring.datasource.username}")
    val username: String,
    @Value("\${spring.datasource.password}")
    val password: String,
    @Value("\${spring.profiles.active}")
    val profile: String,
) {

    @Bean
    fun dataSource(properties: DataSourceProperties): DataSource {

        val dataSource = DriverManagerDataSource()
        dataSource.setDriverClassName(driverClassName)
        dataSource.url = url
        dataSource.username = username
        dataSource.password = password

        val hikariConfig = HikariConfig()
        hikariConfig.maximumPoolSize = 10
        hikariConfig.dataSource = dataSource
        hikariConfig.connectionTimeout = 30000

        return HikariDataSource(hikariConfig)
    }

    @Bean
    fun sqlSessionFactory(dataSource: DataSource): SqlSessionFactoryBean {
        val factory = SqlSessionFactoryBean()

        factory.setDataSource(dataSource)

        factory.setMapperLocations(
            *PathMatchingResourcePatternResolver().getResources("classpath:/mapper/**/*.xml"))

        return factory
    }

    @Bean
    fun sqlSession(sqlSessionFactory: SqlSessionFactory) = SqlSessionTemplate(sqlSessionFactory)

}