package com.pet.service.impl;

import com.pet.dto.PetDto;
import com.pet.entity.Owner;
import com.pet.entity.Pet;
import com.pet.entity.PetType;
import com.pet.exception.EntityNotFoundException;
import com.pet.exception.ErrorCode;
import com.pet.exception.OwnerNotFoundException;
import com.pet.exception.PetNotFoundException;
import com.pet.mapper.PetMapper;
import com.pet.repository.OwnerRepository;
import com.pet.repository.PetRepository;
import com.pet.repository.PetTypeRepository;
import com.pet.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {
    private final PetRepository petRepository;
    private final OwnerRepository ownerRepository;
    private final PetTypeRepository petTypeRepository;
    private final PetMapper petMapper;

    @Override
    public Set<PetDto> findAll() {
        return StreamSupport
                .stream(petRepository.findAll().spliterator(), false)
                .map(petMapper::petToPetDto)
                .collect(Collectors.toSet());
    }

    @Override
    public PetDto findById(Long id) throws RuntimeException {
        Pet pet = petRepository.findById(id).orElseThrow(() -> new PetNotFoundException(ErrorCode.PET_NOT_FOUND.getErrMsgKey()));
        PetDto petDto = petMapper.petToPetDto(pet);
        return petDto;
    }

    @Override
    public PetDto save(PetDto object) {
        Owner owner = ownerRepository.findById(object.getOwnerId()).orElse(null);
        if(Objects.isNull(owner)){
            throw new OwnerNotFoundException(ErrorCode.OWNER_NOT_FOUND.getErrMsgKey());
        }
        PetType petType = petTypeRepository.findById(object.getPetType().getId()).orElse(null);
        if(Objects.isNull(petType)){
            throw new EntityNotFoundException(ErrorCode.PET_TYPE_NOT_FOUND);
        }

        return petMapper.petToPetDto(
                petRepository.save(petMapper.petDtoToPet(object))
        );
    }

    @Override
    public void delete(PetDto object) {
        petRepository.delete(petMapper.petDtoToPet(object));
    }

    @Override
    public void deleteById(Long id) {
        Pet pet = petRepository.findById(id).orElse(null);
        if(Objects.isNull(pet)){
            throw new OwnerNotFoundException(ErrorCode.ENTITY_NOT_FOUND.getErrMsgKey());
        }
        petRepository.deleteById(id);
    }
}
