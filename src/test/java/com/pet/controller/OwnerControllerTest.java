package com.pet.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.pet.PetclinicSpringbootApplication;
import com.pet.dto.OwnerDto;
import com.pet.exception.OwnerNotFoundException;
import com.pet.service.OwnerService;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@TestPropertySource(locations = {"classpath:application-test.yml"}, properties = "management.port=0")
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class OwnerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private Faker faker;
    @MockBean
    private OwnerService ownerService;

    @Test
    public void getAllOwner_normalCase() throws Exception {
        given(ownerService.findAll()).willReturn(new HashSet<>());
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/owners");
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    public void getAllOwner_notFound() throws Exception {
        given(ownerService.findAll()).willReturn(Collections.emptySet());
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/owners-get");
        mockMvc.perform(requestBuilder)
                .andExpect(status().isNotFound());
    }

    @Test
    public void getOwner_normalCase() throws Exception {
        OwnerDto ownerDto = OwnerDto.builder()
                .id(1L)
                .build();
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/owners/1");
        given(ownerService.findById(1L)).willReturn(ownerDto);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").value(1L));

    }

    @Test
    public void getOwner_notFound() throws Exception {
        given(ownerService.findById(-1L)).willThrow(OwnerNotFoundException.class);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/owners/-1");
        mockMvc.perform(requestBuilder)
                .andExpect(status().isNotFound());

    }

    @Test
    public void createNewOwner_normalCase() throws Exception {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String telephone = faker.phoneNumber().cellPhone();
        String address = faker.address().fullAddress();
        String city = faker.address().cityName();
        OwnerDto ownerDto = OwnerDto.builder()
                .firstName(firstName)
                .lastName(lastName)
                .telephone(telephone)
                .address(address)
                .city(city)
                .build();
        given(ownerService.save(ownerDto)).willReturn(ownerDto);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/owners")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(ownerDto));
        mockMvc.perform(requestBuilder)
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.firstName").value(firstName))
                .andExpect(jsonPath("$.lastName").value(lastName))
                .andExpect(jsonPath("$.telephone").value(telephone));

    }

    @Test
    public void createNewOwner_throwPreconditionFail() throws Exception {
        String lastName = faker.name().lastName();
        String telephone = faker.phoneNumber().cellPhone();
        String address = faker.address().fullAddress();
        String city = faker.address().cityName();
        OwnerDto ownerDto = OwnerDto.builder()
                .firstName(null)
                .lastName(lastName)
                .telephone(telephone)
                .address(address)
                .city(city)
                .build();
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/owners")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(ownerDto));
        mockMvc.perform(requestBuilder)
                .andExpect(status().isPreconditionFailed());
    }
}