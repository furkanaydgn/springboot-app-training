package com.example.PetClinicApplication.service;

import com.example.PetClinicApplication.exception.InternalServerException;
import com.example.PetClinicApplication.model.Owner;

import java.util.List;

public interface PetClinicService {

    List<Owner> findOwners();
    List<Owner> findOwners(String lastname);
    Owner findOwner(long id) throws InternalServerException;
    void createOwner(Owner owner );
    void updateOwner(Owner owner);
    void deleteOwner(long id);

}
