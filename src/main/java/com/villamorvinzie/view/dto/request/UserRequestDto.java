package com.villamorvinzie.view.dto.request;

import com.villamorvinzie.view.enums.UserRole;
import java.util.List;

public record UserRequestDto(
        String username, String email, String password, List<UserRole> roles) {}
