package com.example.PetClinicApplication.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "pet_Table")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "petClinicSeqGen")
    @SequenceGenerator(name ="petClinicSeqGen",sequenceName = "petclinic_seq")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "Birth_date")
    private Date birthdate;

    @ManyToOne
    @JsonBackReference
    private Owner owner;


    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthdate=" + birthdate +
                ", owner=" + owner +
                '}';
    }
}
