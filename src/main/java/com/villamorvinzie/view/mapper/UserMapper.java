package com.villamorvinzie.view.mapper;

import com.villamorvinzie.view.domain.User;
import com.villamorvinzie.view.dto.UserDto;

public class UserMapper {

  public static User toEntity(UserDto userDto) {
    return new User(null, userDto.username(), userDto.email(), userDto.password(), userDto.roles());
  }

  public static UserDto toDto(User user) {
    return new UserDto(user.getUsername(), user.getEmail(), user.getPassword(), user.getRoles());
  }
}
