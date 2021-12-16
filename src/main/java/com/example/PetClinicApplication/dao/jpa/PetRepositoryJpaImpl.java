package com.example.PetClinicApplication.dao.jpa;

import com.example.PetClinicApplication.dao.PetRepository;
import com.example.PetClinicApplication.model.Pet;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("petRepository")
public class PetRepositoryJpaImpl implements PetRepository {

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Pet FindById(Long id) {
        return entityManager.find(Pet.class, id);
    }

    @Override
    public List<Pet> FindByOwnerId(Long ownerId) {
        return entityManager.createQuery("select x from Pet as x where Owner.id=:ownerId" , Pet.class)
                .setParameter("ownerId", ownerId).getResultList();
    }

    @Override
    public void create(Pet pet) {
        entityManager.persist(pet);

    }

    @Override
    public Pet update(Pet pet) {
        return entityManager.merge(pet);

    }

    @Override
    public void delete(Long id) {
        entityManager.remove(entityManager.getReference(Pet.class, id));

    }

    @Override
    public void deleteByOwnerId(long ownerId) {
        entityManager.createQuery("delete from Pet where owner.id = :ownerId").setParameter("ownerId", ownerId)
                .executeUpdate();
    }
}
