package com.teamone.clivet.model.user.dto;

import com.teamone.clivet.model.user.User;
import com.teamone.clivet.model.user.UserRole;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.Transient;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Accessors(chain = true)
public class UserLoginDto {

    private Long id;
    private String username;
    private String password;
    private UserRole role;
    private String firstName;
    private String lastName;
    @Transient
    private String token;



    public static UserLoginDto mapToDto(User user){
        return new UserLoginDto()
                .setId(user.getId())
                .setUsername(user.getUsername())
                .setPassword(user.getPassword())
                .setRole(user.getRole())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setToken(user.getToken());
    }

    public static User mapToModel(UserLoginDto dto) {
        return new User()
                .setId(dto.getId())
                .setUsername(dto.getUsername())
                .setPassword(dto.getPassword())
                .setRole(dto.getRole())
                .setFirstName(dto.getFirstName())
                .setLastName(dto.getLastName())
                .setToken(dto.getToken());
    }





}
