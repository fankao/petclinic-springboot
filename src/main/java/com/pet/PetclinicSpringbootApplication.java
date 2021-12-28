package com.pet;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Pet clinic API", version = "1.0", description = "Pet clinic api specification"))
public class PetclinicSpringbootApplication {
    public static void main(String[] args) {
        SpringApplication.run(PetclinicSpringbootApplication.class, args);
    }

}
