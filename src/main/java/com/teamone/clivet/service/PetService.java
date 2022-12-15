package com.teamone.clivet.service;

import com.teamone.clivet.model.pet.Pet;
import com.teamone.clivet.model.pet.dto.PetRegisterDto;

import java.util.List;
import java.util.Optional;

public interface PetService {

    public PetRegisterDto save(PetRegisterDto dto, Long ownerId);
    public List<PetRegisterDto> getPetsByOwnerId(Long ownerId);
    Optional<Pet> findById(Long id);
    Pet findPetByOwnerId(Long ownerId, Long petId);

    public void deletePet(Pet pet);
}
