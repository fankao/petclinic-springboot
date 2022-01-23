package com.pet.mapper;

import com.pet.dto.PetDto;
import com.pet.entity.Pet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

import java.util.Set;

@Mapper(componentModel = "spring", uses = {VisitMapper.class, PetTypeMapper.class}, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface PetMapper {
    @Mapping(source = "birthDate", target = "birthDate", dateFormat = "yyyy-MM-dd")
    @Mapping(source = "owner.id",target = "ownerId")
    PetDto petToPetDto(Pet pet);

    @Mapping(source = "birthDate", target = "birthDate", dateFormat = "yyyy-MM-dd")
    @Mapping(source = "ownerId",target = "owner.id")
    Pet petDtoToPet(PetDto petDto);

    Set<PetDto> petsToPetDtos(Set<Pet> pets);

    Set<Pet> petDtosToPets(Set<PetDto> petDtos);
}
