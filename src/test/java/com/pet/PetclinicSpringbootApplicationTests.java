package com.pet;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(classes = PetclinicSpringbootApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = {"classpath:application-test.yml"},properties = "management.port=0")
@ActiveProfiles("test")
class PetclinicSpringbootApplicationTests {

    @Test
    void contextLoads() {
    }

}
