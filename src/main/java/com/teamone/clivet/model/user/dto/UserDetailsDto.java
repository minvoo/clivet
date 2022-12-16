package com.teamone.clivet.model.user.dto;

import com.teamone.clivet.model.pet.Pet;
import com.teamone.clivet.model.user.User;
import com.teamone.clivet.model.user.UserRole;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Accessors(chain = true)
public class UserDetailsDto {

    private String username;
    private String email;
    private String firstName;
    private String lastName;


    public static UserDetailsDto mapToDto(User user){
        return new UserDetailsDto()
                .setUsername(user.getUsername())
                .setEmail(user.getEmail())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName());
    }
}
