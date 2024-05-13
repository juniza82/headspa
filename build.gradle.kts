import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.2.5"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "1.9.23"
    kotlin("plugin.spring") version "1.9.23"
}

group = "com.yoon"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:2.3.0")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.springframework.boot:spring-boot-starter-batch")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")

    implementation("org.jetbrains.kotlin:kotlin-reflect")

    implementation("io.awspring.cloud:spring-cloud-starter-aws-secrets-manager-config:2.4.4")

    implementation("org.opensearch.client:spring-data-opensearch-starter:1.2.0")
    implementation("org.springframework.boot:spring-boot-starter-data-elasticsearch")
    implementation ("org.springframework.boot:spring-boot-starter-data-redis")

    implementation("com.fasterxml.jackson.core:jackson-core:2.14.2")

    implementation("org.apache.commons:commons-csv:1.8")

    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.4")

    implementation("io.jsonwebtoken:jjwt-api:0.11.5")
    implementation("io.jsonwebtoken:jjwt-impl:0.11.5")
    implementation("io.jsonwebtoken:jjwt-jackson:0.11.5")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.6.0")

    implementation("org.apache.poi:poi:5.2.2")
    implementation("org.apache.poi:poi-ooxml:5.2.2")
    implementation("org.bgee.log4jdbc-log4j2:log4jdbc-log4j2-jdbc4.1:1.16")

    implementation("com.github.maricn:logback-slack-appender:1.4.0") // slack log
    implementation("net.gpedro.integrations.slack:slack-webhook:1.4.0") // slack messenger

    // 한타영타 뒤집기 (https://github.com/kimcore/inko.kt)
    implementation("com.github.kimcore", "inko.kt", "1.2")

    // Expedia API
    implementation("com.expediagroup:rapid-sdk:2.0.0")

    runtimeOnly("org.postgresql:postgresql")
//    runtimeOnly("mysql:mysql-connector-java")
//    runtimeOnly("com.mysql:mysql-connector-j")
    runtimeOnly("org.mariadb.jdbc:mariadb-java-client")

    developmentOnly("org.springframework.boot:spring-boot-devtools")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.bootBuildImage {
    builder.set("paketobuildpacks/builder-jammy-base:latest")
}