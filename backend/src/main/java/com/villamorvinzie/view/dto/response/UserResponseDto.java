package com.villamorvinzie.view.dto.response;

import com.villamorvinzie.view.enums.UserRole;
import java.util.List;

public record UserResponseDto(
        String id, String username, String email, String password, List<UserRole> roles) {}
