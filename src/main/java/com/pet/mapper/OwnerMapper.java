package com.pet.mapper;

import com.pet.dto.OwnerDto;
import com.pet.dto.PetDto;
import com.pet.entity.Owner;
import com.pet.entity.Pet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

import java.util.Set;

@Mapper(componentModel = "spring",nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,uses = {PetMapper.class})
public interface OwnerMapper {
    OwnerDto ownerToOwnerDto(Owner owner);

    Owner ownerDtoToOwner(OwnerDto ownerDto);

    Set<OwnerDto> ownersToOwnerDtos(Set<Owner> owners);

    Set<Owner> ownerDtosToOwners(Set<OwnerDto> ownerDtos);
}
