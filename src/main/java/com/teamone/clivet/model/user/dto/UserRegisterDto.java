package com.teamone.clivet.model.user.dto;

import com.teamone.clivet.model.user.User;
import com.teamone.clivet.model.user.UserRole;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.Size;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Accessors(chain = true)
public class UserRegisterDto {

    private Long  id;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private UserRole role;


    public static UserRegisterDto mapToDto(User user){
        return new UserRegisterDto()
                .setId(user.getId())
                .setUsername(user.getUsername())
                .setPassword(user.getPassword())
                .setEmail(user.getEmail())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setRole(user.getRole());
    }
}
