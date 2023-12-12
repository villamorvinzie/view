package com.villamorvinzie.view.dto;

import java.util.List;

public record UserDto(String username, String email, String password, List<String> roles) {
}