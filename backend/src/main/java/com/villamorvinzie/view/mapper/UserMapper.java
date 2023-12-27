package com.villamorvinzie.view.mapper;

import com.villamorvinzie.view.domain.User;
import com.villamorvinzie.view.dto.request.UserRequestDto;
import com.villamorvinzie.view.dto.response.UserResponseDto;

public class UserMapper {
    public static User toEntity(UserRequestDto userDto) {
        return new User(null, userDto.username(), userDto.email(), userDto.password(), userDto.roles());
    }

    public static User toEntity(UserResponseDto userDto) {
        return new User(
                userDto.id(), userDto.username(), userDto.email(), userDto.password(), userDto.roles());
    }

    public static UserResponseDto toDto(User user) {
        return new UserResponseDto(
                user.getId(), user.getUsername(), user.getEmail(), user.getPassword(), user.getRoles());
    }
}
