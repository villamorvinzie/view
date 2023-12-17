package com.villamorvinzie.view.dto;

import com.villamorvinzie.view.enums.UserRole;
import java.util.List;

public record UserDto(String username, String email, String password, List<UserRole> roles) {}
