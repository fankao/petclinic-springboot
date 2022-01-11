package com.pet.service.impl;

import com.pet.dto.PetDto;
import com.pet.entity.Pet;
import com.pet.exception.ErrorCode;
import com.pet.exception.PetNotFoundException;
import com.pet.mapper.PetMapper;
import com.pet.repository.PetRepository;
import com.pet.service.PetService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {
    private final PetRepository petRepository;
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
        return petRepository.findById(id).map(petMapper::petToPetDto).orElseThrow(() -> new PetNotFoundException(ErrorCode.PET_NOT_FOUND.getErrMsgKey()));
    }

    @Override
    public PetDto save(PetDto object) {
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
        petRepository.deleteById(id);
    }
}
