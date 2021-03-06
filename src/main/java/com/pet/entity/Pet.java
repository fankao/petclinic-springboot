package com.pet.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pets")
public class Pet extends BaseEntity {
    @Builder
    public Pet(Long id, Timestamp createdDate, Timestamp updatedDate, String name, PetType petType, Owner owner, Set<Visit> visits, LocalDate birthDate) {
        super(id, createdDate, updatedDate);
        this.name = name;
        this.petType = petType;
        this.owner = owner;
        this.visits = visits;
        this.birthDate = birthDate;
    }
    @Column(name = "name")
    @NotBlank(message = "Pet name must not blank")
    private String name;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private PetType petType;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "pet")
    private Set<Visit> visits = new HashSet<>();

    @Column(name = "birth_date")
    private LocalDate birthDate;
}
