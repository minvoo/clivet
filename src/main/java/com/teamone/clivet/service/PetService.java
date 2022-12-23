package com.teamone.clivet.service;

import com.teamone.clivet.model.pet.Pet;
import com.teamone.clivet.model.pet.dto.PetRegisterDto;
import com.teamone.clivet.model.pet.dto.PetUpdateDto;

import java.util.List;
import java.util.Optional;

public interface PetService {

    public PetRegisterDto save(PetRegisterDto dto, Long ownerId);
    public List<PetRegisterDto> getPetsByOwnerId(Long ownerId);
    Optional<Pet> findById(Long id);
    List<PetRegisterDto> getPetsByUserName();
    public void deletePet(Pet pet);
    Pet findPetByOwnerId(Long ownerId, Long petId);

    PetRegisterDto getPet(Long petId, Long ownerId);

    PetRegisterDto getPetLogged(Long petId);
    PetUpdateDto updatePet(PetUpdateDto dto, Long ownerId, Long petId);
}
