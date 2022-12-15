package com.teamone.clivet.service;

import com.teamone.clivet.exception.ElementNotFoundException;
import com.teamone.clivet.model.appointment.dto.AppointmentDto;
import com.teamone.clivet.model.pet.Pet;
import com.teamone.clivet.model.pet.dto.PetRegisterDto;
import com.teamone.clivet.model.pet.dto.PetUpdateDto;
import com.teamone.clivet.model.user.User;
import com.teamone.clivet.repository.PetRepository;
import com.teamone.clivet.utils.CurrentUserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PetServiceImpl implements PetService {



    @Autowired
    private PetRepository petRepository;
    @Autowired
    private UserService userService;


    public PetRegisterDto save(PetRegisterDto dto, Long ownerId) {

        Optional<User> userOp = userService.findById(ownerId);
        User owner = userOp.orElseThrow(() -> new ElementNotFoundException("Owner", "id", ownerId.toString()));
        dto.setOwner(owner);
        Pet savedPet = petRepository.saveAndFlush(PetRegisterDto.mapToModel(dto));

        return PetRegisterDto.mapToDto(savedPet);
    }

    @Override
    public List<PetRegisterDto> getPetsByOwnerId(Long ownerId) {

        User owner = userService.findById(ownerId).orElseThrow(
                () -> new ElementNotFoundException("Owner", "id", ownerId.toString()));
        List<Pet> pets = petRepository.findByOwner(owner);
        return PetRegisterDto.mapToDto(pets);

    }

    @Override
    public Pet findById(Long id) {
        Pet pet = null;
        Optional<Pet> optionalPet = petRepository.findById(id);
        if (optionalPet.isPresent()) {
            pet = optionalPet.get();
        } else {
            throw new ElementNotFoundException("Pet", "id", id.toString());
        }
        return pet;
    }

    @Override
    public List<PetRegisterDto> getPetsByUserName() {
        String currentUserName = CurrentUserUtils.getCurrentUserName();
        User user = userService.findByUsername(currentUserName)
                .orElseThrow(()->new ElementNotFoundException("User","name",currentUserName));
        List<Pet> byOwner = petRepository.findByOwner(user);
        return PetRegisterDto.mapToDto(byOwner);


    @Override
    public Pet findPetByOwnerId(Long ownerId, Long petId) {
        List<PetRegisterDto> pets = getPetsByOwnerId(ownerId);

        PetRegisterDto petToDelete = pets.stream()
                .filter(dto -> dto.getId() == petId)
                .findFirst()
                .get();

        return PetRegisterDto.mapToModel(petToDelete);
    }

    @Override
    public PetUpdateDto updatePet(PetUpdateDto dto, Long ownerId, Long petId) {
        Pet pet = findPetByOwnerId(ownerId, petId);
        if(pet == null){
            throw new ElementNotFoundException("Pet", "id", petId.toString());
        }
            pet.setAge(dto.getAge());
            pet.setWeight(dto.getWeight());

            Pet petUpdated = petRepository.saveAndFlush(pet);

            return PetUpdateDto.mapToDto(petUpdated);
    }

    @Override
    public void deletePet(Pet pet) {
        petRepository.delete(pet);
    }

}

