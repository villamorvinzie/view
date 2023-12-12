package com.villamorvinzie.view.service;

import org.springframework.stereotype.Service;

import com.villamorvinzie.view.domain.User;
import com.villamorvinzie.view.dto.UserDto;
import com.villamorvinzie.view.mapper.UserMapper;
import com.villamorvinzie.view.repository.UserRepository;

import reactor.core.publisher.Mono;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<UserDto> createUser(UserDto userDto) {
        return userRepository.existsByUsernameIgnoreCase(userDto.username()).flatMap(isPresent -> {
            if (isPresent) {
                throw new RuntimeException("User already exists.");
            }
            Mono<User> monoUser = userRepository.save(UserMapper.toEntity(userDto));
            return monoUser.flatMap(user -> Mono.just(UserMapper.toDto(user)));
        });
    }

    public Mono<UserDto> readUser(String username) {
        Mono<User> monoUser = userRepository.findByUsername(username);
        return monoUser.flatMap(user -> Mono.just(UserMapper.toDto(user)));
    }

    public Mono<UserDto> updateUser(String username, UserDto userDto) {
        userRepository.existsByUsernameIgnoreCase(userDto.username()).doOnSuccess(isPresent -> {
            if (isPresent) {
                throw new RuntimeException("User already exists.");
            }
        });

        return userRepository.findByUsername(username).flatMap(user -> {
            user.setEmail(userDto.email());
            user.setPassword(userDto.password());
            user.setRoles(userDto.roles());
            user.setUsername(userDto.username());
            Mono<User> monoUser = userRepository.save(user);
            return monoUser.flatMap(mappedUser -> Mono.just(UserMapper.toDto(mappedUser)));
        });
    }

    public Mono<Void> deleteUser(String username) {
        return userRepository.deleteByUsername(username);
    }
}
