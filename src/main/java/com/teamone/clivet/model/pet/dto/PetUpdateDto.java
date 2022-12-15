package com.teamone.clivet.model.pet.dto;

import com.teamone.clivet.model.pet.Pet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Accessors(chain = true)
public class PetUpdateDto {

    private int age;
    private int weight;


    public static PetUpdateDto mapToDto(Pet pet) {
        return new PetUpdateDto()
                .setAge(pet.getAge())
                .setWeight(pet.getWeight());

}
    }
