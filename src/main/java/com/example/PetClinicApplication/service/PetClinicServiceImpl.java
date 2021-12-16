package com.example.PetClinicApplication.service;

import com.example.PetClinicApplication.dao.OwnerRepository;
import com.example.PetClinicApplication.exception.InternalServerException;
import com.example.PetClinicApplication.exception.OwnerNotFoundException;
import com.example.PetClinicApplication.model.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;


public class PetClinicServiceImpl implements PetClinicService{
    private OwnerRepository ownerRepository;

    @Autowired
    public void setOwnerRepository(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public List<Owner> findOwners() {
        return ownerRepository.findAll();
    }

    @Override
    public List<Owner> findOwners(String lastname) {
        return ownerRepository.FindByLastName(lastname);
    }


    @Override
    public Owner findOwner(long id) throws InternalServerException {
        System.out.println("find ownerrr");
        Owner owner = ownerRepository.FindById(id);
        if(owner == null) {
            throw new OwnerNotFoundException("Owner not found with id :" + id);
        }
        return owner;
    }



    @Override
    public void createOwner(Owner owner) {
         ownerRepository.create(owner);
    }

    @Override
    public void updateOwner(Owner owner) {
        ownerRepository.update(owner);
    }

    @Override
    public void deleteOwner(long id) {
    ownerRepository.delete(id);
    }
}
