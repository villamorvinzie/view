package com.villamorvinzie.view.service;

import com.villamorvinzie.view.domain.User;
import com.villamorvinzie.view.dto.request.UserRequestDto;
import com.villamorvinzie.view.dto.response.UserResponseDto;
import com.villamorvinzie.view.exception.UserAlreadyExistAuthenticationException;
import com.villamorvinzie.view.mapper.UserMapper;
import com.villamorvinzie.view.repository.UserRepository;
import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDto createUser(UserRequestDto userDto)
            throws UserAlreadyExistAuthenticationException {
        if (userRepository.existsByUsername(userDto.username())) {
            throw new UserAlreadyExistAuthenticationException("User already exists.");
        }
        User user = UserMapper.toEntity(userDto);
        user.setPassword(passwordEncoder.encode(userDto.password()));
        user = userRepository.save(user);
        return UserMapper.toDto(user);
    }

    public UserResponseDto readUser(String username) {
        Optional<User> optUser = userRepository.findByUsername(username);
        if (optUser.isEmpty()) {
            throw new UsernameNotFoundException("Username not found.");
        }
        return UserMapper.toDto(optUser.get());
    }

    public UserResponseDto updateUser(String username, UserRequestDto userDto)
            throws UserAlreadyExistAuthenticationException {
        User savedUser;

        if (userRepository.existsByUsername(userDto.username())) {
            throw new UserAlreadyExistAuthenticationException("Username already exists.");
        }

        if (!userRepository.existsByUsername(username)) {
            throw new UsernameNotFoundException("Username not found.");
        }

        savedUser =
                userRepository
                        .findByUsername(username)
                        .map(
                                user -> {
                                    user.setEmail(userDto.email());
                                    user.setPassword(passwordEncoder.encode(userDto.password()));
                                    user.setRoles(userDto.roles());
                                    user.setUsername(userDto.username());
                                    return userRepository.save(user);
                                })
                        .get();

        return UserMapper.toDto(savedUser);
    }

    public void deleteUser(String username) {
        if (!userRepository.existsByUsername(username)) {
            throw new UsernameNotFoundException("Username not found.");
        }
        userRepository.deleteByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<User> optUser = userRepository.findByUsername(username);
        return optUser.orElseThrow(() -> new UsernameNotFoundException("Username not found."));
    }
}
