package com.example.portailci.swagger;

import com.fasterxml.classmate.TypeResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ApiListingScannerPlugin;
import springfox.documentation.spi.service.contexts.DocumentationContext;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.readers.operation.CachingOperationNameGenerator;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.singleton;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select() //
                .apis(RequestHandlerSelectors.basePackage("com.example.portailci.exposition"))
                .paths(Predicates.not(PathSelectors.regex("/error.*"))) //
                .build() //
                .apiInfo(apiInfo())
                .securitySchemes(Arrays.asList(securityScheme()))
                .securityContexts(Arrays.asList(securityContexts()));
    }

    private springfox.documentation.spi.service.contexts.SecurityContext securityContexts() {
        return SecurityContext.builder()
                .securityReferences(Arrays.asList(basicAuthReference()))
                .forPaths(PathSelectors.any())
                .build();
    }

    private SecurityScheme securityScheme() {
        return new BasicAuth("basicAuth");
    }

    private SecurityReference basicAuthReference() {
        return new SecurityReference("basicAuth", new AuthorizationScope[0]);
    }

    ApiInfo apiInfo() {
        return new ApiInfoBuilder()//
                .title("Swagger PortailCI service") //
                .description("Portail CI application doc") //
                .license("private use") //
                .licenseUrl("none") //
                .termsOfServiceUrl("") //
                .version("1.0") //
                .contact(new Contact("", "", "SomeOne@bnpparibas.com")) //
                .build();
    }
}

@Component
class LoginControllerScanner implements ApiListingScannerPlugin {

    private final CachingOperationNameGenerator operationNames;

    public LoginControllerScanner(CachingOperationNameGenerator operationNames) {
        this.operationNames = operationNames;
    }

    @Override
    public List<ApiDescription> apply(DocumentationContext context) {
        return new ArrayList<>(
                Arrays.asList(
                        new ApiDescription(
                                "security",
                                "/logout",
                                "This is a logout controller",
                                Collections.singletonList(
                                        new OperationBuilder(operationNames)
                                                .authorizations(new ArrayList<>())
                                                .codegenMethodNameStem("logout") //<3>
                                                .method(HttpMethod.POST)
                                                .notes("This is a logout")
                                                .responseMessages(
                                                        singleton(new ResponseMessageBuilder().build()))
                                                .responseModel(new springfox.documentation.schema.ModelRef("string"))
                                                .build()),
                                false),

                        new ApiDescription(
                                "security",
                                "/login",
                                "This is a login controller",
                                Collections.singletonList( //<2>
                                        new OperationBuilder(operationNames)
                                                .authorizations(new ArrayList<>())
                                                .codegenMethodNameStem("Login") //<3>
                                                .method(HttpMethod.POST)
                                                .notes("This is a login")
                                                .parameters(
                                                        Arrays.asList(
                                                                new ParameterBuilder()
                                                                        .order(0)
                                                                        .description("username")
                                                                        .type(new TypeResolver().resolve(String.class))
                                                                        .name("username")
                                                                        .parameterType("form")
                                                                        .parameterAccess("access")
                                                                        .required(true)
                                                                        .modelRef(new ModelRef(
                                                                                "string")) //<5>
                                                                        .build(),
                                                                new ParameterBuilder()
                                                                        .order(1)
                                                                        .description("password")
                                                                        .type(new TypeResolver().resolve(String.class))
                                                                        .name("password")
                                                                        .parameterType("form")
                                                                        .parameterAccess("access")
                                                                        .required(true)
                                                                        .modelRef(new ModelRef(
                                                                                "string")) //<5>
                                                                        .build()))
                                                .responseMessages(
                                                        singleton(new ResponseMessageBuilder().build()))
                                                .responseModel(new springfox.documentation.schema.ModelRef("string")) //<7>
                                                .build()),
                                false)));
    }

    @Override
    public boolean supports(DocumentationType delimiter) {
        return DocumentationType.SWAGGER_2.equals(delimiter);
    }
}
