package com.teamone.clivet.model.pet;


import com.teamone.clivet.model.user.User;
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
    private User owner;



}
