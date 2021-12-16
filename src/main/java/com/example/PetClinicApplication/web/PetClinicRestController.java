package com.example.PetClinicApplication.web;

import com.example.PetClinicApplication.exception.InternalServerException;
import com.example.PetClinicApplication.exception.OwnerNotFoundException;
import com.example.PetClinicApplication.model.Owner;
import com.example.PetClinicApplication.service.PetClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class PetClinicRestController {

    @Autowired
    private PetClinicService petClinicService;

    @RequestMapping(method = RequestMethod.DELETE, value = "/owner/{id}")
    public ResponseEntity<?> deleteOwner(@PathVariable long id) {
        try {
            petClinicService.deleteOwner(id);
            return ResponseEntity.ok().build();
        } catch (OwnerNotFoundException ex) {
            throw ex;
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/owner/{id}")
    public ResponseEntity<?> updateOwner(@PathVariable long id, @RequestBody Owner ownerRequest) {

        try {
            Owner owner = petClinicService.findOwner(id);
            owner.setFirstname(ownerRequest.getFirstname());
            owner.setLastname(ownerRequest.getLastname());
            petClinicService.updateOwner(owner);
            return ResponseEntity.ok().build();

        } catch (InternalServerException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }


    @RequestMapping(method = RequestMethod.POST, value = "/owner")
    public ResponseEntity<URI> createOwner(@RequestBody Owner owner) {
        try {
            petClinicService.createOwner(owner);
            long id = owner.getId();
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
            return ResponseEntity.created(location).build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/owners")
    public ResponseEntity<List<Owner>> getOwners() {
        List<Owner> owners = petClinicService.findOwners();
        System.out.printf("asdasdasdadasdads");
        return ResponseEntity.ok(owners);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/owner")
    public ResponseEntity<List<Owner>> getOwners(@RequestParam("ln") String lastName) {
        List<Owner> owners = petClinicService.findOwners(lastName);
        return ResponseEntity.ok(owners);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/owner/{id}")
    public ResponseEntity<Owner> getOwner(@PathVariable("id") Long id) {
        try {
            System.out.printf("id" + id);
            Owner owner = petClinicService.findOwner(id);
            System.out.printf("asdasd    public ResponseEntity<Owner> getOwner ...." + owner);
            return ResponseEntity.ok(owner);
        } catch (InternalServerException ex) {
            return ResponseEntity.ok().build();
        }


    }


}
