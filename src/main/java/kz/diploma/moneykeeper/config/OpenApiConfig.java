package kz.diploma.moneykeeper.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openApi() {

        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("Moneykeeper Service API")
                        .description(
                                "Moneykeeper service for accounting personal budget. "
                                        + "Manages and provides APIs for budget management")
                        .contact(new Contact()
                                .name("Moneykeeper")
                        )
                        .version("1.0.0")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));

    }

}
