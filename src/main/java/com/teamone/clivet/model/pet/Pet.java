package com.teamone.clivet.model.pet;


import com.teamone.clivet.model.appointment.Appointment;
import com.teamone.clivet.model.user.User;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Pet")
@Accessors(chain = true, fluent = false)
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NonNull
    @Size(min=2, message = "Pet name must contain at least 2 characters")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "weight")
    private int weight;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name="user_id")
    @ToString.Exclude
    private User owner;

    // owner of the relation
    @OneToMany(orphanRemoval = true, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.LAZY, mappedBy = "pet")
    private List<Appointment> appointments;


    public void addAppointment(Appointment appointment) {
        if (appointments == null) {
            appointments = new ArrayList<>();
        }
        appointments.add(appointment);
        appointment.setPet(this);
    }

    public Pet setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
        return this;
    }
}
