package com.teamone.clivet.model.pet.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.teamone.clivet.model.appointment.Appointment;
import com.teamone.clivet.model.pet.KindOfPet;
import com.teamone.clivet.model.pet.Pet;
import com.teamone.clivet.model.user.User;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Accessors(chain = true)
public class PetRegisterDto {

    private Long id;
    private String name;
    private int age;
    private int weight;
    private KindOfPet kind;
    @JsonIgnore
    private User owner;



    public static Pet mapToModel(PetRegisterDto dto) {
        return new Pet()
                .setId(dto.getId())
                .setName(dto.getName())
                .setAge(dto.getAge())
                .setWeight(dto.getWeight())
                .setKind(dto.getKind())
                .setOwner(dto.getOwner());
//                .setAppointments(new ArrayList<>());
    }

    public static PetRegisterDto mapToDto(Pet pet) {
        return new PetRegisterDto()
                .setId(pet.getId())
                .setName(pet.getName())
                .setAge(pet.getAge())
                .setWeight(pet.getWeight())
                .setOwner(pet.getOwner());
    }

    public static List<PetRegisterDto> mapToDto(List<Pet> pets) {
        return pets.stream()
                .map(PetRegisterDto::mapToDto)
                .collect(Collectors.toList());
    }
}
