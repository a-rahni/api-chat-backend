package fr.m2i.apichat.config;


/*
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${info.project.name}")
    private String projectName;

    @Value("${info.project.desc}")
    private String projectDescription;

    @Value("${info.project.version}")
    private String projectVersion;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("fr.m2i.apicrm"))
                .paths(PathSelectors.ant("/v1/**"))
                .build();
    }

}
*/

import com.fasterxml.classmate.TypeResolver;
//import fr.m2i.apichat.exception.ApiException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {
    @Bean
    public Docket apiDocket(TypeResolver tr) {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(metadata())
                // Do not automatically add 401, 403 and 404 responses to every operation
                .useDefaultResponseMessages(false)
                // Since this isn't explicitly mentioned in any Controller, we have to add it manually
                //.additionalModels(tr.resolve(ApiException.class))
                .select()
                .apis(RequestHandlerSelectors.basePackage("fr.m2i.apichat.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /*
Générer des informations d'interface ,Y compris le titre,Contacts, etc.
*/
    private ApiInfo metadata() {
        return new ApiInfoBuilder()
                .title(" API-chat Backend  certification M2I  Christian et  Rahni  ")
                .description("En cas de doute,Contact possible")
                .contact(new Contact("Api-Chat","http://","contact@m2i.fr"))
                .version("1.0")
                .build();
    }
    /**
     * This is necessary to ensure that the swagger UI resources are available.
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }


}