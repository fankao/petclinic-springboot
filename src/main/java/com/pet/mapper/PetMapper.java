package com.pet.mapper;

import com.pet.dto.PetDto;
import com.pet.entity.Pet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper(componentModel = "spring", uses = {VisitMapper.class, PetTypeMapper.class})
public interface PetMapper {
    @Mapping(source = "owner.id",target = "ownerId")
    @Mapping(source = "birthDate",target = "birthDate",dateFormat = "yyyy-MM-dd")
    PetDto petToPetDto(Pet pet);

    @Mapping(source = "ownerId",target = "owner.id")
    @Mapping(source = "birthDate",target = "birthDate",dateFormat = "yyyy-MM-dd")
    Pet petDtoToPet(PetDto petDto);

    Set<PetDto> petsToPetDtos(Set<Pet> pets);

    Set<Pet> petDtosToPets(Set<PetDto> petDtos);
}
