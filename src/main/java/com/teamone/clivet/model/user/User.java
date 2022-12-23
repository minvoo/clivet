package com.teamone.clivet.model.user;

import com.teamone.clivet.model.pet.Pet;
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
@Table(name = "User", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"}),
        @UniqueConstraint(columnNames = {"email"})})
@Accessors(chain = true, fluent = false)
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false)
    @NonNull
    private String username;

    @Column(name = "password", nullable = false)
    @NonNull
    @Size(min =8, message = "Password must have contain at least 8 characters")
    private String password;

    @Column(name = "email",nullable = false)
    private String email;

    @Column(name= "first_name",nullable = false)
    private String firstName;

    @Column(name="last_name", nullable = false)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name="role")
    private UserRole role;

    @Transient
    private String token;

    // ------ Relations -------

    // owner of the relation
    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE},
    mappedBy = "owner", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Pet> pets;


    public void addPet(Pet pet) {
        if (pets == null) {
            pets = new ArrayList<>();
        }
        pets.add(pet);
        pet.setOwner(this);
    }

}