package com.teamone.clivet.repository;

import com.teamone.clivet.model.pet.Pet;
import com.teamone.clivet.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

    List<Pet> findByOwner(User owner);
}
