package com.teamone.clivet.repository;

import com.teamone.clivet.model.appointment.Appointment;
import com.teamone.clivet.model.pet.Pet;
import com.teamone.clivet.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    List<Appointment> findByUser(User user);
}
