package com.pet.controller;

import com.pet.dto.PetDto;
import com.pet.service.PetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/pets")
@RequiredArgsConstructor
@Slf4j
public class PetController {
    private final PetService petService;
    @GetMapping
    public ResponseEntity<Set<PetDto>> getAll(){
        return ResponseEntity.ok(petService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Set<PetDto>> getPetById(@PathVariable Long id){
        return ResponseEntity.ok(petService.findAll());
    }
    @PostMapping
    public ResponseEntity<PetDto> create (@RequestBody PetDto petDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(petService.save(petDto));
    }
    @PutMapping()
    public ResponseEntity<PetDto> update (@RequestBody PetDto petDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(petService.save(petDto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete (@PathVariable Long id){
        petService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
