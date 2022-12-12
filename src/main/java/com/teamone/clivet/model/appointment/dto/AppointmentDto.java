package com.teamone.clivet.model.appointment.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.teamone.clivet.model.appointment.Appointment;
import com.teamone.clivet.model.pet.Pet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Accessors(chain = true)
public class AppointmentDto {


    private LocalDate date;
    private String description;
    private String medicine;
    private Long cost;
    @JsonIgnore
    private Pet pet;

    public static Appointment mapToModel(AppointmentDto dto) {
        return new Appointment()
                .setDate(dto.getDate())
                .setDescription(dto.getDescription())
                .setMedicine(dto.getMedicine())
                .setCost(dto.getCost())
                .setPet(dto.getPet());
    }
    public static AppointmentDto mapToDto(Appointment appointment) {
        return new AppointmentDto()
                .setDate(appointment.getDate())
                .setDescription(appointment.getDescription())
                .setMedicine(appointment.getMedicine())
                .setCost(appointment.getCost())
                .setPet(appointment.getPet());
    }
}
