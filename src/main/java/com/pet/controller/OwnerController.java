package com.pet.controller;

import com.pet.dto.OwnerDto;
import com.pet.service.OwnerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@Controller
@RequiredArgsConstructor
@RequestMapping("/owners")
@Slf4j
@Tag(name = "Owner",description = "REST API for owner information")
public class OwnerController {
    private final OwnerService ownerService;
    @Operation(
            summary = "Get all owner",
            description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
    })
    @GetMapping
    public ResponseEntity<Set<OwnerDto>> getAllOwner() {
        log.info("Calling api GET getAllOwner...");
        return ResponseEntity.ok(ownerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerDto> getOwner(@PathVariable("id") Long id) {
        log.info("Calling api GET getOwner...");
        return ResponseEntity.ok(ownerService.findById(id));
    }

    @PostMapping
    public ResponseEntity<OwnerDto> createNewOwner(@Valid @RequestBody OwnerDto ownerDto) {
        log.info("Calling api POST createNewOwner...");
        return ResponseEntity.status(HttpStatus.CREATED).body(ownerService.save(ownerDto));
    }

    @PutMapping
    public ResponseEntity<OwnerDto> updateOwner(@RequestBody OwnerDto ownerDto) {
        log.info("Calling api PUT updateOwner...");
        return ResponseEntity.status(HttpStatus.CREATED).body(ownerService.save(ownerDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteOwnerById(@PathVariable Long id) {
        log.info("Calling api DELETE deleteOwnerById...");
        ownerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
