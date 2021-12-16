package com.example.PetClinicApplication;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "petclinic")
public class PetClinicProperties
{
    private boolean displayOwnerwithPets =false;

    public boolean isDisplayOwnerwithPets() {
        return displayOwnerwithPets;
    }

    public void setDisplayOwnerwithPets(boolean displayOwnerwithPets) {
        this.displayOwnerwithPets = displayOwnerwithPets;
    }
}
