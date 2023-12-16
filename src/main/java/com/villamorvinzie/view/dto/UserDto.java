package com.villamorvinzie.view.dto;

import java.util.List;

import com.villamorvinzie.view.enums.UserRole;

public record UserDto(String username, String email, String password, List<UserRole> roles) {
}