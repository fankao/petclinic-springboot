package com.pet.mapper;

import com.pet.dto.VisitDto;
import com.pet.entity.PetType;
import com.pet.entity.Visit;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface VisitMapper {
    VisitDto visitToVisitDto(Visit visit);
    Visit visitDtoToVisit(VisitDto visitDto);
    Set<VisitDto> visitToVisitDto(Set<Visit> visit);
    Set<Visit> visitDtoToVisit(Set<VisitDto> visitDto);
}
