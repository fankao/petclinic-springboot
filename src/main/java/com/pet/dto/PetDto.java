package com.pet.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PetDto {
    private Long id;
    private String name;
    private PetTypeDto petType;
    private Set<VisitDto> visits;
    private LocalDate birthDate;
}
