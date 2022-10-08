package com.blitech.app4docker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * @author Martin_Tresor
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket postsApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("Blitech").apiInfo(apiInfo()).select()
                .paths(regex("/api/.*")
                        ).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Employee Service")
                .description("Sample Documentation Generateed Using SWAGGER2 for our Employee Rest API")
                .termsOfServiceUrl("https://termify.io/")
                .license("Martin tresor License")
                .licenseUrl("https://www.gnu.org/licenses/gpl-3.0.html")
                .version("2.0").build();
    }
}
