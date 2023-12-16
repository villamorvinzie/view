package com.villamorvinzie.view.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.villamorvinzie.view.dto.UserDto;
import com.villamorvinzie.view.dto.request.AuthLoginRequestDto;
import com.villamorvinzie.view.dto.response.JwtAuthResponseDto;
import com.villamorvinzie.view.service.AuthService;

@RestController
@RequestMapping(path = "/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(path = "/login")
    public JwtAuthResponseDto login(@RequestBody AuthLoginRequestDto requestDto) {
        return authService.login(requestDto);
    }

    @PostMapping(path = "/register")
    public JwtAuthResponseDto register(@RequestBody UserDto requestDto) {
        return authService.register(requestDto);
    }
}
