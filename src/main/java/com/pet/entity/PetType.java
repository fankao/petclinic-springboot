package com.pet.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "types")
public class PetType extends BaseEntity{
    @Builder
    public PetType(Long id, Timestamp createdDate, Timestamp updatedDate, String name) {
        super(id, createdDate, updatedDate);
        this.name = name;
    }

    @Column(name = "name")
    @NotBlank(message = "Pet Type name must not blank")
    private String name;
}
