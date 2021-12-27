package com.pet.mapper;

import com.pet.dto.PetTypeDto;
import com.pet.entity.Pet;
import com.pet.entity.PetType;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface PetTypeMapper {
    PetTypeDto petTypeToPetType(PetType petType);
    PetType petTypeTypeDtoToPet(PetTypeDto petTypeDto);
    Set<PetTypeDto> petTypesToPetTypeDtos(Set<PetType> petTypes);
    Set<PetType> petTypeDtosToPetTypes(Set<PetTypeDto> petTypeDtos);
}