package com.teamone.clivet.model.appointment.dto;

import com.teamone.clivet.model.pet.KindOfPet;
import com.teamone.clivet.model.pet.Pet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDetailsDto {
    private Long id;
    private LocalDate date;
    private String description;
    private String medicine;
    private Double cost;
    private Pet pet;
    private KindOfPet kind;
    private boolean cancelled;
}
