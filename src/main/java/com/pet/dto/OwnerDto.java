package com.pet.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OwnerDto {
    private Long id;
    @NotBlank(message = "Owner first name must not be blank")
    private String firstName;
    @NotBlank(message = "Owner last name must not be blank")
    private String lastName;
    @NotBlank(message = "Owner address must not be blank")
    private String address;
    private String city;
    private String telephone;
}
