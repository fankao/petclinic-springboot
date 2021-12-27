package com.pet.service.impl;

import com.pet.dto.OwnerDto;
import com.pet.entity.Owner;
import com.pet.mapper.OwnerMapper;
import com.pet.repository.OwnerRepository;
import com.pet.service.OwnerService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {
    private final OwnerRepository ownerRepository;
    private final OwnerMapper ownerMapper;

    @Override
    public Set<OwnerDto> findAll() {
        Set<Owner> owners = StreamSupport.stream(ownerRepository.findAll().spliterator(), false)
                .collect(Collectors.toSet());
        return ownerMapper.ownersToOwnerDtos(owners);
    }

    @Override
    public OwnerDto findById(Long id) throws NotFoundException {
        Owner owner = ownerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Not found the owner with id %d", id)));
        return ownerMapper.ownerToOwnerDto(owner);
    }

    @Override
    public OwnerDto save(OwnerDto object) {
        return ownerMapper.ownerToOwnerDto(ownerRepository.save(ownerMapper.ownerDtoToOwner(object)));
    }

    @Override
    public void delete(OwnerDto object) {
        ownerRepository.delete(ownerMapper.ownerDtoToOwner(object));
    }

    @Override
    public void deleteById(Long id) {
        ownerRepository.deleteById(id);
    }
}
