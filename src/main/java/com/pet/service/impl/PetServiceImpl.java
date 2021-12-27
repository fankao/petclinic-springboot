package com.pet.service.impl;

import com.pet.entity.Pet;
import com.pet.exception.ErrorCode;
import com.pet.exception.PetNotFoundException;
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

    @Override
    public Set<Pet> findAll() {
        return StreamSupport
                .stream(petRepository.findAll().spliterator(), false)
                .collect(Collectors.toSet());
    }

    @Override
    public Pet findById(Long id) throws RuntimeException {
        return petRepository.findById(id).orElseThrow(() -> new PetNotFoundException(ErrorCode.PET_NOT_FOUND.getErrMsgKey()));
    }

    @Override
    public Pet save(Pet object) {
        return petRepository.save(object);
    }

    @Override
    public void delete(Pet object) {
        petRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        petRepository.deleteById(id);
    }
}
