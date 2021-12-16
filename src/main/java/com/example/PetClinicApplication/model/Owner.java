package com.example.PetClinicApplication.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="owner_table")
@XmlRootElement
@AllArgsConstructor
@NoArgsConstructor
public class Owner implements Serializable {

    @Id
    @SequenceGenerator(name="seq_owner_table",allocationSize = 1)
    @GeneratedValue(generator = "petClinicSeqGen",strategy = GenerationType.SEQUENCE)
    private long id2;

    @Column(name="first_name")
    private String firstname;

    @Column(name="last_name")
    private String lastname;


    @OneToMany(mappedBy="owner",orphanRemoval = true)
    private Set<Pet> pets= new HashSet<>();

    public long getId() {
        return id2;
    }

    public void setId(long id) {
        this.id2 = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @XmlTransient
    public Set<Pet> getPets() {
        return pets;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + id2 +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
