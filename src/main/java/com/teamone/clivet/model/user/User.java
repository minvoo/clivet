package com.teamone.clivet.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "User", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"}),
        @UniqueConstraint(columnNames = {"email"})})
@Accessors(chain = true, fluent = false)
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



}