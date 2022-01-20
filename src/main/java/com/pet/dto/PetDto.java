package com.pet.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PetDto {
    private Long id;
    @NotBlank(message = "must not be blank")
    private String name;
    private PetTypeDto petType;
    private Set<VisitDto> visits;
    private OwnerDto owner;

    @NotBlank(message = "Must not be blank")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String birthDate;
}
