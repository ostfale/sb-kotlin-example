package de.ostfale.sbkotlin.sbkotlin.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.info.BuildProperties
import org.springframework.boot.info.GitProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger.web.*
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.util.*

/**
 *  Required informations are available inside beans BuildProperties and GitProperties. We just have to inject them into Swagger
 *  configuration class as shown below. We set them as optional to prevent from application startup
 *  failure in case they are not present on classpath.
 *  Path: http://localhost:8080/swagger-ui.html
 * Created :  17.08.2019
 *
 *  @author : Uwe Sauerbrei
 */
@Configuration
@EnableSwagger2
class SwaggerConfig {

    @Autowired
    lateinit var build: Optional<BuildProperties>
    @Autowired
    lateinit var git: Optional<GitProperties>

    @Bean
    fun api(): Docket {
        var version = "1.0"
        if (build.isPresent && git.isPresent) {
            val buildInfo = build.get()
            val gitInfo = git.get()
            version = "${buildInfo.version}-${gitInfo.shortCommitId}-${gitInfo.branch}"
        }
        return Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo(version))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths { it.equals("/persons") }
                .build()
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
    }

    @Bean
    fun uiConfig(): UiConfiguration {
        return UiConfiguration(java.lang.Boolean.TRUE,
                java.lang.Boolean.FALSE,
                1,
                1,
                ModelRendering.MODEL,
                java.lang.Boolean.FALSE,
                DocExpansion.LIST,
                java.lang.Boolean.FALSE,
                null,
                OperationsSorter.ALPHA,
                java.lang.Boolean.FALSE,
                TagsSorter.ALPHA,
                UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS,
                null)
    }

    private fun apiInfo(version: String): ApiInfo {
        return ApiInfoBuilder()
                .title("API - Person Service")
                .description("Persons Management")
                .version(version)
                .build()
    }
}