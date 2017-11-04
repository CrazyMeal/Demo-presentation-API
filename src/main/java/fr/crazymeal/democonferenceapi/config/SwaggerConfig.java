package fr.crazymeal.democonferenceapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.not;
import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Created by Crazymeal on 04/11/2017.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

        @Bean
        public Docket api() {
            return new Docket(DocumentationType.SWAGGER_2)
                .select()
                    .apis(RequestHandlerSelectors.any())
                    .paths(regex("/presentation/.*"))
                    .build()
                .pathMapping("/")
                .apiInfo(this.metadata());
        }

        private ApiInfo metadata() {
            return new ApiInfoBuilder()
                    .title("API pour présentation Postman")
                    .description("Cette API permet de créer des présentations. Une présentation a un speaker (celui qui anime la présentation) et des participants")
                    .build();
        }
}
