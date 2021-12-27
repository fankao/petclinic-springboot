package com.pet.service.impl;

import com.pet.entity.Pet;
import com.pet.entity.PetType;
import com.pet.repository.PetRepository;
import com.pet.repository.PetTypeRepository;
import com.pet.service.PetTypeService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class PetTypeServiceImpl implements PetTypeService {
    private final PetTypeRepository petTypeRepository;

    @Override
    public Set<PetType> findAll() {
        return StreamSupport.stream(petTypeRepository.findAll().spliterator(), false)
                .collect(Collectors.toSet());
    }

    @Override
    public PetType findById(Long id) throws NotFoundException {
        return petTypeRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Not found the pet type with id %d", id)));
    }

    @Override
    public PetType save(PetType object) {
        return petTypeRepository.save(object);
    }

    @Override
    public void delete(PetType object) {
        petTypeRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        petTypeRepository.deleteById(id);
    }
}
