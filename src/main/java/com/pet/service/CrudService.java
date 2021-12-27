package com.pet.service;

import javassist.NotFoundException;

import java.util.Set;

public interface CrudService<T,ID> {
    Set<T> findAll();
    T findById(ID id) throws RuntimeException;
    T save(T object);
    void delete(T object);
    void deleteById(ID id);
}
