package com.example.PetClinicApplication.dao;

import com.example.PetClinicApplication.model.Pet;

import java.util.List;

public interface PetRepository {
    Pet FindById(Long id );
    List<Pet> FindByOwnerId(Long ownerId);
    void create(Pet pet);
    Pet update(Pet pet);
    void delete(Long id);
    void deleteByOwnerId(long ownerId);
}
