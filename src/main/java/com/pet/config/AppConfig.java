package com.pet.config;

import com.github.javafaker.Faker;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

@Configuration
public class AppConfig {
    private Faker faker;

    @Bean
    public Faker configFaker() {
        return new Faker(Locale.ENGLISH);
    }
    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("Pet clinic API").version("1.0"));
    }
}
