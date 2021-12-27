package com.pet.mapper;

import com.pet.dto.OwnerDto;
import com.pet.entity.Owner;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper(componentModel = "spring", uses = {PetMapper.class})
public interface OwnerMapper {
    OwnerDto ownerToOwnerDto(Owner owner);

    Owner ownerDtoToOwner(OwnerDto ownerDto);

    Set<OwnerDto> ownersToOwnerDtos(Set<Owner> owners);

    Set<Owner> ownerDtosToOwners(Set<OwnerDto> ownerDtos);
}
