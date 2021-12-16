package com.example.PetClinicApplication.dao.mem;

import com.example.PetClinicApplication.dao.OwnerRepository;
import com.example.PetClinicApplication.exception.InternalServerException;
import com.example.PetClinicApplication.model.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.PetClinicApplication.service.PetClinicService;

import java.util.List;

@Service
public class PetClinicServiceİmpl implements PetClinicService {

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
        Owner owner = ownerRepository.FindById(id);
        if (owner == null) {
            throw new InternalServerException("Owner not found with id :" + id);
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
