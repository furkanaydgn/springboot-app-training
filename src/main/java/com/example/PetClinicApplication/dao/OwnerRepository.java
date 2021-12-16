package com.example.PetClinicApplication.dao;

import com.example.PetClinicApplication.model.Owner;

import java.util.List;

public interface OwnerRepository
{
    List<Owner> findAll();
    Owner FindById(long id);
    List<Owner> FindByLastName(String lastname);
    void create(Owner owner);
    Owner update(Owner owner);
    void delete(long id);


}
