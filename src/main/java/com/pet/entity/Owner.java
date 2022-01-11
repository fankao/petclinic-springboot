package com.pet.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "owners")
public class Owner extends Person{
    @Builder
    public Owner(Long id, Timestamp createdDate, Timestamp updatedDate, String firstName, String lastName, String address, String telephone, String city, Set<Pet> pets) {
        super(id, createdDate, updatedDate, firstName, lastName);
        this.address = address;
        this.telephone = telephone;
        this.city = city;
        this.pets = pets;
    }

    @Column(name = "address")
    @NotBlank(message = "Owner address must not blank")
    private String address;

    @Column(name = "telephone")
    @NotBlank(message = "Owner telephone must not blank")
    private String telephone;

    @Column(name = "city")
    @NotBlank(message = "Owner city must not blank")
    private String city;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "owner")
    private Set<Pet> pets = new HashSet<Pet>();
}
