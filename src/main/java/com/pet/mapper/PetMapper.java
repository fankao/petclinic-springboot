package com.pet.mapper;

import com.pet.dto.PetDto;
import com.pet.dto.PetTypeDto;
import com.pet.entity.Pet;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring", uses = {VisitMapper.class, PetTypeMapper.class})
public interface PetMapper {
    PetDto petToPetDto(Pet pet);

    Pet petDtoToPet(PetDto petDto);

    Set<PetDto> petsToPetDtos(Set<Pet> pets);

    Set<Pet> petDtosToPets(Set<PetDto> petDtos);
}
