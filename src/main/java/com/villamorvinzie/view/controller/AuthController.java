package com.villamorvinzie.view.controller;

import com.villamorvinzie.view.dto.UserDto;
import com.villamorvinzie.view.dto.request.AuthLoginRequestDto;
import com.villamorvinzie.view.dto.response.JwtAuthResponseDto;
import com.villamorvinzie.view.exception.UserAlreadyExistAuthenticationException;
import com.villamorvinzie.view.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(path = "/login")
    public ResponseEntity<JwtAuthResponseDto> login(@RequestBody AuthLoginRequestDto requestDto) {
        return ResponseEntity.ok().body(authService.login(requestDto));
    }

    @PostMapping(path = "/register")
    public ResponseEntity<JwtAuthResponseDto> register(@RequestBody UserDto requestDto)
            throws UserAlreadyExistAuthenticationException {
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(authService.register(requestDto));
    }
}
