package com.example.PetClinicApplication.dao.jpa;

import com.example.PetClinicApplication.dao.OwnerRepository;
import com.example.PetClinicApplication.model.Owner;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository("ownerRepository")
public class OwnerRepositoryjpaImpl implements OwnerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Owner> findAll() {
        return entityManager.createQuery("select x from Owner as x", Owner.class).getResultList();
    }

    @Override
    public Owner FindById(long id) {
        return entityManager.find(Owner.class, id);
    }

    @Override
    public List<Owner> FindByLastName(String lastname) {
        return null;
    }

    @Override
    public void create(Owner owner) {
        entityManager.persist(owner);
    }

    @Override
    public Owner update(Owner owner) {
        return entityManager.merge(owner);
    }

    @Override
    public void delete(long id) {
        entityManager.remove(entityManager.getReference(Owner.class, id));
    }
}
