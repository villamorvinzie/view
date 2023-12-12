package com.villamorvinzie.view.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.villamorvinzie.view.dto.UserDto;
import com.villamorvinzie.view.service.UserService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public Mono<UserDto> createUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @GetMapping(path = "/{username}")
    public Mono<UserDto> readUser(@PathVariable String username) {
        return userService.readUser(username);
    }

    @PutMapping(path = "/{username}")
    public Mono<UserDto> updateUser(@PathVariable String username, @RequestBody UserDto userDto) {
        return userService.updateUser(username, userDto);
    }

    @DeleteMapping(path = "/{username}")
    public Mono<Void> deleteUser(@PathVariable String username) {
        return userService.deleteUser(username);
    }
}
