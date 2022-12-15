package com.teamone.clivet.controller;

import com.teamone.clivet.exception.ElementNotFoundException;
import com.teamone.clivet.exception.handler.UserRestExceptionHandler;
import com.teamone.clivet.model.pet.Pet;
import com.teamone.clivet.model.pet.dto.PetRegisterDto;
import com.teamone.clivet.model.user.User;
import com.teamone.clivet.service.PetService;
import com.teamone.clivet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/api")
@RestController
public class PetRestController {

    @Autowired
    private PetService petService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRestExceptionHandler exceptionHandler;

    @PostMapping("/owner/{ownerId}/pets")
    public ResponseEntity<?> savePet(@RequestBody PetRegisterDto petDto,
                                     @PathVariable("ownerId") Long ownerId) {

        Optional<User> owner = userService.findById(ownerId);

        if (owner.isEmpty()) {
            return exceptionHandler.handleException
                    (HttpStatus.NOT_FOUND, new ElementNotFoundException("User", "ID", ownerId.toString()));
        }


        return new ResponseEntity<>(petService.save(petDto, ownerId), HttpStatus.CREATED);
    }

    @GetMapping("/owner/{ownerId}/pets")
    public ResponseEntity<?> listOwnersPets(@PathVariable("ownerId") Long ownerId) {


        Optional<User> owner = userService.findById(ownerId);

        if (owner.isEmpty()) {
            return exceptionHandler.handleException
                    (HttpStatus.NOT_FOUND, new ElementNotFoundException("User", "ID", ownerId.toString()));
        }


        return new ResponseEntity<>(petService.getPetsByOwnerId(ownerId), HttpStatus.CREATED);

    }

    @DeleteMapping("/owner/{ownerId}/pets/{petId}")
    public ResponseEntity<?> deletePet(@PathVariable Long ownerId, @PathVariable Long petId) {
        Pet pet = petService.findPetByOwnerId(ownerId, petId);
        if (pet == null) {
            return exceptionHandler.handleException
                    (HttpStatus.NOT_FOUND, new ElementNotFoundException("Pet", "ID", petId.toString()));
        }
        petService.deletePet(pet);
        return new ResponseEntity<>(HttpStatus.OK);

    }


}