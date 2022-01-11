package com.pet.bootstrap;

import com.github.javafaker.Faker;
import com.pet.service.OwnerService;
import com.pet.service.PetService;
import com.pet.service.PetTypeService;
import com.pet.service.VisitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;
    private final PetService petService;
    private final PetTypeService petTypeService;
    private final VisitService visitService;
    private final Faker faker;
    @Override
    public void run(String... args) throws Exception {
        int count = petTypeService.findAll().size();
        if(count == 0){
            loadData();
        }
    }

    private void loadData() {
//        PetType dog = new PetType();
//        dog.setName("Dog");
//        PetType savedDogPetType = petTypeService.save(dog);
//
//        PetType cat = new PetType();
//        cat.setName("Cat");
//        PetType savedCatPetType = petTypeService.save(cat);
//
//        log.info("Loaded Pet type...");
//
//        Owner owner1 = new Owner();
//        owner1.setFirstName("Michael");
//        owner1.setLastName("Weston");
//        owner1.setAddress("123 Brickerel");
//        owner1.setCity("Miami");
//        owner1.setTelephone("1231231234");
//
//        Pet mikesPet = new Pet();
//        mikesPet.setPetType(savedDogPetType);
//        mikesPet.setOwner(owner1);
//        mikesPet.setBirthDate(LocalDate.now());
//        mikesPet.setName("Rosco");
//        owner1.getPets().add(mikesPet);
//
//        ownerService.save(owner1);
//
//        Owner owner2 = new Owner();
//        owner2.setFirstName("Fiona");
//        owner2.setLastName("Glenanne");
//        owner2.setAddress("123 Brickerel");
//        owner2.setCity("Miami");
//        owner2.setTelephone("1231231234");
//
//        Pet fionasCat = new Pet();
//        fionasCat.setName("Just Cat");
//        fionasCat.setOwner(owner2);
//        fionasCat.setBirthDate(LocalDate.now());
//        fionasCat.setPetType(savedCatPetType);
//        owner2.getPets().add(fionasCat);
//
//        ownerService.save(owner2);
//
//        Visit catVisit = new Visit();
//        catVisit.setPet(fionasCat);
//        catVisit.setDate(LocalDate.now());
//        catVisit.setDescription("Sneezy Kitty");
//
//        visitService.save(catVisit);
//
//        log.info("Loaded owner...");
    }
}
