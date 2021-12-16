package com.example.PetClinicApplication.dao.mem;

import com.example.PetClinicApplication.dao.OwnerRepository;
import com.example.PetClinicApplication.model.Owner;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;


@Repository
public class OwnerRepositoryInMemoryImpl implements OwnerRepository {

    private Map<Long,Owner> ownersMap=new HashMap<>();

    public OwnerRepositoryInMemoryImpl() {

        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstname("Kenan");
        owner1.setLastname("Sevindik");

        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setFirstname("Hümeyra");
        owner2.setLastname("Sevindik");

        Owner owner3 = new Owner();
        owner3.setId(3L);
        owner3.setFirstname("Salim");
        owner3.setLastname("Sevindik");

        Owner owner4 = new Owner();
        owner4.setId(4L);
        owner4.setFirstname("yunus");
        owner4.setLastname("emre");

        ownersMap.put(owner1.getId(), owner1);
        ownersMap.put(owner2.getId(), owner2);
        ownersMap.put(owner3.getId(), owner3);
        ownersMap.put(owner4.getId(), owner4);

    }

    @Override
    public List<Owner> findAll() {
        return new ArrayList<>(ownersMap.values());
    }

    @Override
    public Owner FindById(long id) {
        return ownersMap.get(id);
    }

    @Override
    public List<Owner> FindByLastName(String lastname) {
        return ownersMap.values().stream().filter(o-> o.getLastname().equals(lastname)).collect(Collectors.toList()) ;
    }

    @Override
    public void create(Owner owner) {
        owner.setId(new Date().getTime());
        ownersMap.put(owner.getId(),owner);
    }

    @Override
    public Owner update(Owner owner) {
       ownersMap.replace(owner.getId(),owner);
       return owner;
    }

    @Override
    public void delete(long id) {
        ownersMap.remove(id);

    }
}
