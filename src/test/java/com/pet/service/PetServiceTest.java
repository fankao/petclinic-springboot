package com.pet.service;

import com.github.javafaker.Faker;
import com.pet.dto.PetDto;
import com.pet.entity.Owner;
import com.pet.entity.Pet;
import com.pet.entity.PetType;
import com.pet.exception.PetNotFoundException;
import com.pet.mapper.*;
import com.pet.repository.PetRepository;
import com.pet.service.impl.PetServiceImpl;
import javassist.NotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.util.ReflectionUtils;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = {
//        PetMapperImpl.class,
//        OwnerMapperImpl.class,
//        VisitMapperImpl.class,
//        PetTypeMapperImpl.class
//})
//@ActiveProfiles("test")
public class PetServiceTest {
    @InjectMocks
    private PetServiceImpl petService;
    @Mock
    private PetRepository petRepository;

    private Faker faker;

    @Spy
    private PetMapper petMapper = Mappers.getMapper(PetMapper.class);

    @BeforeEach
    public void setup() {
        faker = new Faker();
        PetTypeMapper petTypeMapper = Mappers.getMapper(PetTypeMapper.class);
        VisitMapper visitMapper = Mappers.getMapper(VisitMapper.class);
        ReflectionTestUtils.setField(petMapper, "visitMapper", visitMapper);
        ReflectionTestUtils.setField(petMapper, "petTypeMapper", petTypeMapper);
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
        Pet pet = Pet.builder()
                .id(1L)
                .name("Juhn")
                .birthDate(LocalDate.now().minusDays(20))
                .owner(Owner.builder().id(1L).build())
                .petType(PetType.builder().id(1L).build())
                .build();
        PetDto petDto = petMapper.petToPetDto(pet);
        when(petRepository.save(pet)).thenReturn(pet);
        assertEquals(pet.getId(), petService.save(petDto).getId());
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