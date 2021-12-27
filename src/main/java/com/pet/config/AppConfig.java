package com.pet.config;

import com.github.javafaker.Faker;
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
}
