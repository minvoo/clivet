package com.teamone.clivet.model.user.dto;

import com.teamone.clivet.model.user.User;
import lombok.*;
import lombok.experimental.Accessors;


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
    private String password;


    public static UserDetailsDto mapToDto(User user){
        return new UserDetailsDto()
                .setUsername(user.getUsername())
                .setEmail(user.getEmail())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setPassword(user.getPassword());
    }

    public static User mapToModel(UserDetailsDto detailsDto) {
        return new User()
                .setUsername(detailsDto.getUsername())
                .setEmail(detailsDto.getEmail())
                .setFirstName(detailsDto.getFirstName())
                .setLastName(detailsDto.getLastName())
                .setPassword(detailsDto.getPassword());
    }
}
