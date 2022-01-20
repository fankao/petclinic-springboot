package com.pet.mapper;

import com.pet.dto.PetDto;
import com.pet.entity.Pet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

import java.util.Set;

@Mapper(componentModel = "spring", uses = {OwnerMapper.class,VisitMapper.class, PetTypeMapper.class}, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface PetMapper {
    @Mapping(source = "birthDate", target = "birthDate", dateFormat = "yyyy-MM-dd")
    PetDto petToPetDto(Pet pet);

    @Mapping(source = "birthDate", target = "birthDate", dateFormat = "yyyy-MM-dd")
    Pet petDtoToPet(PetDto petDto);

    Set<PetDto> petsToPetDtos(Set<Pet> pets);

    Set<Pet> petDtosToPets(Set<PetDto> petDtos);


}
