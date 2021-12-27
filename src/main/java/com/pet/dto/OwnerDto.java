package com.pet.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    private Set<PetDto> pets;
}
