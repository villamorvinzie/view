package com.villamorvinzie.view.controller;

import com.villamorvinzie.view.dto.UserDto;
import com.villamorvinzie.view.exception.UserAlreadyExistAuthenticationException;
import com.villamorvinzie.view.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto)
            throws UserAlreadyExistAuthenticationException {
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(userService.createUser(userDto));
    }

    @GetMapping(path = "/{username}")
    public ResponseEntity<UserDto> readUser(@PathVariable String username) {
        return ResponseEntity.ok().body(userService.readUser(username));
    }

    @PutMapping(path = "/{username}")
    public ResponseEntity<UserDto> updateUser(
            @PathVariable String username, @RequestBody UserDto userDto)
            throws UserAlreadyExistAuthenticationException {
        return ResponseEntity.ok().body(userService.updateUser(username, userDto));
    }

    @DeleteMapping(path = "/{username}")
    public void deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
    }
}
