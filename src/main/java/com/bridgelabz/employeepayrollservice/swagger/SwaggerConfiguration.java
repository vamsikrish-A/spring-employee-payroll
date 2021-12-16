package com.bridgelabz.employeepayrollservice.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Purpose: Adding Swagger Configuration for employee payroll application
 *
 * @author Vamsi Krishna
 * @since 2021-12-15
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    /**
     * Purpose: method specifies the swagger to API(Application Programming Interface)
     * to show on Swagger UI console.
     *
     * @return the docket link which has the information about API
     */
    @Bean
    public Docket postApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Employeepayroll")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bridgelabz.employeepayrollservice.controller"))
                .build();
    }

    /**
     * Purpose: method used to add extra data which will give user a proper idea about
     * the API information in the Swagger UI console
     *
     * @return the swagger API information
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Employeepayroll application")
                .description("Sample Documentation Generated Using SWAGGER2 for Employee pay roll Rest API")
                .termsOfServiceUrl("https://github.com/vamsikrish-A")
                .license("License")
                .licenseUrl("https://github.com/vamsikrish-A")
                .version("1.0")
                .build();
    }

}
