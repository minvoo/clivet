package com.teamone.clivet.model.user.dto;

import com.teamone.clivet.model.user.User;
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
    @Transient
    private String token;



    public static UserLoginDto mapToDto(User user){
        return new UserLoginDto()
                .setId(user.getId())
                .setUsername(user.getUsername())
                .setPassword(user.getPassword())
                .setToken(user.getToken());
    }

    public static User mapToModel(UserLoginDto dto) {
        return new User()
                .setId(dto.getId())
                .setUsername(dto.getUsername())
                .setPassword(dto.getPassword())
                .setToken(dto.getToken());
    }





}
