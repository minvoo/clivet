package com.teamone.clivet.model.appointment.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.teamone.clivet.model.pet.Pet;
import lombok.*;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@ToString
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDto {
    private Long id;
    private LocalDate date;
    private String description;
    private String medicine;
    private Double cost;
//    @JsonIgnore
//    private Pet pet;
    @ManyToOne
    private Pet pet;


}
