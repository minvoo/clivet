package com.teamone.clivet.service;

import com.teamone.clivet.model.pet.dto.PetRegisterDto;

import java.util.List;

public interface PetService {

    public PetRegisterDto save(PetRegisterDto dto, Long ownerId);
    public List<PetRegisterDto> getPetsByOwnerId(Long ownerId);
}
