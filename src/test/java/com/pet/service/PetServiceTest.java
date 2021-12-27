package com.pet.service;

import com.github.javafaker.Faker;
import com.pet.entity.Pet;
import com.pet.exception.PetNotFoundException;
import com.pet.repository.PetRepository;
import com.pet.service.impl.PetServiceImpl;
import javassist.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PetServiceTest {
    @InjectMocks
    private PetServiceImpl petService;
    @Mock
    private PetRepository petRepository;

    private Faker faker;

    @BeforeEach
    public void setUp() {
        faker = new Faker();
    }

    @Test
    public void findAll_NotEqualZero_ListOfPetEqualTen() throws Exception {
        Set<Pet> pets = listOfPet();
        when(petRepository.findAll()).thenReturn(pets);
        assertNotEquals(0, petService.findAll().size());
    }

    @Test
    public void findAll_EqualZero_ListOfPetEqualZero() throws Exception {
        Set<Pet> pets = new HashSet<>();
        when(petRepository.findAll()).thenReturn(pets);
        assertEquals(0, petService.findAll().size());
    }

    @Test
    public void findById_ThrowPetNotFoundException_PetIsNotFound() {
        Long id = faker.number().randomNumber();
        when(petRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(PetNotFoundException.class, () -> petService.findById(id));
    }

    @Test
    public void findById_ReturnTrue_PetIsFound() throws NotFoundException {
        Long id = faker.number().randomNumber();
        String petName = "Cun";
        when(petRepository.findById(id)).thenReturn(Optional.of(Pet.builder().name(petName).build()));
        assertTrue(petService.findById(id).getName().equals(petName));
    }
    @Test
    public void savePet_ReturnTrue_PetNameIsValid() throws Exception{

    }

    private Set<Pet> listOfPet() {
        Set<Pet> pets = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            Pet pet = Pet.builder()
                    .name(faker.animal().name())
                    .build();
            pets.add(pet);
        }
        return pets;
    }

}