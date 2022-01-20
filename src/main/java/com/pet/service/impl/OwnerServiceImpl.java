package com.pet.service.impl;

import com.pet.dto.OwnerDto;
import com.pet.entity.Owner;
import com.pet.exception.ErrorCode;
import com.pet.exception.OwnerNotFoundException;
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
    public OwnerDto findById(Long id) throws RuntimeException {
        Owner owner = ownerRepository.findById(id)
                .orElseThrow(() -> new OwnerNotFoundException(ErrorCode.ENTITY_NOT_FOUND.getErrMsgKey()));
        return ownerMapper.ownerToOwnerDto(owner);
    }

    @Override
    public OwnerDto save(OwnerDto object) {
        Owner owner = ownerMapper.ownerDtoToOwner(object);
        return ownerMapper.ownerToOwnerDto(ownerRepository.save(owner));
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
