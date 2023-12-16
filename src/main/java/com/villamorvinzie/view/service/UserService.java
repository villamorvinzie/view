package com.villamorvinzie.view.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.villamorvinzie.view.domain.User;
import com.villamorvinzie.view.dto.UserDto;
import com.villamorvinzie.view.mapper.UserMapper;
import com.villamorvinzie.view.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto createUser(UserDto userDto) {
        if (userRepository.existsByUsernameIgnoreCase(userDto.username())) {
            throw new RuntimeException("User already exists.");
        }
        User user = UserMapper.toEntity(userDto);
        user.setPassword(passwordEncoder.encode(userDto.password()));
        user = userRepository.save(user);
        return UserMapper.toDto(user);
    }

    public UserDto readUser(String username) {
        Optional<User> optUser = userRepository.findByUsername(username);
        if (optUser.isEmpty()) {
            throw new RuntimeException("User not found!");
        }
        return UserMapper.toDto(optUser.get());
    }

    public UserDto updateUser(String username, UserDto userDto) {
        User savedUser;

        if (userRepository.existsByUsernameIgnoreCase(userDto.username())) {
            throw new RuntimeException("User already exists.");
        }

        savedUser = userRepository.findByUsername(username).map(user -> {
            user.setEmail(userDto.email());
            user.setPassword(passwordEncoder.encode(userDto.password()));
            user.setRoles(userDto.roles());
            user.setUsername(userDto.username());
            return userRepository.save(user);
        }).get();

        return UserMapper.toDto(savedUser);
    }

    public void deleteUser(String username) {
        userRepository.deleteByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optUser = userRepository.findByUsername(username);
        return optUser.orElseThrow(() -> new RuntimeException("User not found."));
    }
}
