package com.pet.service.impl;

import com.pet.entity.Visit;
import com.pet.repository.VisitRepository;
import com.pet.service.VisitService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class VisitServiceImpl implements VisitService {
    private final VisitRepository visitRepository;

    @Override
    public Set<Visit> findAll() {
        return StreamSupport.stream(visitRepository.findAll().spliterator(), false)
                .collect(Collectors.toSet());
    }

    @Override
    public Visit findById(Long id) throws NotFoundException {
        return visitRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Not found the visit with id %s", id)));
    }

    @Override
    public Visit save(Visit visit) {
        return visitRepository.save(visit);
    }

    @Override
    public void delete(Visit visit) {
        visitRepository.delete(visit);
    }

    @Override
    public void deleteById(Long id) {
        visitRepository.deleteById(id);
    }
}
