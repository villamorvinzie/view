package com.villamorvinzie.view.service;

import com.villamorvinzie.view.dto.request.AuthLoginRequestDto;
import com.villamorvinzie.view.dto.request.UserRequestDto;
import com.villamorvinzie.view.dto.response.JwtAuthResponseDto;
import com.villamorvinzie.view.dto.response.UserResponseDto;
import com.villamorvinzie.view.exception.UserAlreadyExistAuthenticationException;
import com.villamorvinzie.view.mapper.UserMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(
            UserService userService, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public JwtAuthResponseDto register(UserRequestDto requestDto)
            throws UserAlreadyExistAuthenticationException {
        UserResponseDto userDto = userService.createUser(requestDto);
        String token = jwtService.generateToken(UserMapper.toEntity(userDto));
        return new JwtAuthResponseDto(token);
    }

    public JwtAuthResponseDto login(AuthLoginRequestDto requestDto) {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(requestDto.username(), requestDto.password());
        authenticationManager.authenticate(token);
        UserDetails loggedUser = userService.loadUserByUsername(requestDto.username());
        if (loggedUser == null) {
            throw new BadCredentialsException("Please check your username and/or password.");
        }
        String jwt = jwtService.generateToken(loggedUser);
        return new JwtAuthResponseDto(jwt);
    }
}
