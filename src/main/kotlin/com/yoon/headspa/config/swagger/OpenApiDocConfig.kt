package kr.co.eoding.hotelota.config.swagger

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

//@Profile(value = ["prod"])
@Configuration
class OpenApiDocConfig {

    @Bean
    fun customOpenApi(@Value("\${springdoc.swagger-ui.version}") appVersion: String): OpenAPI {
        return OpenAPI()
            .info(
                Info().also { info ->
                    info.title = "Yoon HeadSpa API"
                    info.version = appVersion
                    info.description = "HeadSpa SpringDoc"
                    info.termsOfService = "https://swagger.io/terms/"
                    info.license =
                        License().also { license ->
                            license.name = "Apache 2.0"
                            license.url = "https://springdoc.org"
                        }
                }
            )
            .addSecurityItem(SecurityRequirement().addList("JWT-Token"))
            .components(
                Components().addSecuritySchemes(
                    "JWT-Token",
                    SecurityScheme().also {
                        it.type = SecurityScheme.Type.HTTP
                        it.scheme = "bearer"
                        it.bearerFormat = "JWT"
                    }
                )
            )
    }
}