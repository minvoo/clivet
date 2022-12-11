package com.teamone.clivet.model.appointment;


import com.teamone.clivet.model.pet.Pet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Appointment")
@Accessors(chain = true, fluent = false)
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="date")
    private LocalDate date;

    @Column(name="description")
    private String description;

    @Column(name = "medicine")
    private String medicine;

    @Column(name = "cost")
    private Long cost;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name="pet_id")
    private Pet pet;
}
