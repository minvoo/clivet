package com.teamone.clivet.model.user.dto;


import com.teamone.clivet.model.user.User;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Accessors(chain = true)
public class UserListDto {

    private Long id;
    private String firstName;
    private String lastName;


    public static UserListDto mapToDto(User user){
        return new UserListDto()
                .setId(user.getId())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName());
    }

    public static User mapToModel(UserListDto dto) {
        return new User()
                .setId(dto.getId())
                .setFirstName(dto.getFirstName())
                .setLastName(dto.getLastName());

    }

    public static List<UserListDto> mapToDto(List<User> users) {
        return users.stream()
                .map(UserListDto::mapToDto)
                .collect(Collectors.toList());
    }





}
