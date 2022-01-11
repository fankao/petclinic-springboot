package com.pet.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PetDto {
    private Long id;
    @NotBlank(message = "must not be blank")
    private String name;
    @NotNull(message = "must not be null")
    private Long ownerId;
    private PetTypeDto petType;
    private Set<VisitDto> visits;

    @NotBlank(message = "Must not be blank")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String birthDate;
}
