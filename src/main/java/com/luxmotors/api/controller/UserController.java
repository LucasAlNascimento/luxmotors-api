package com.luxmotors.api.controller;

import com.luxmotors.api.domain.users.User;
import com.luxmotors.api.domain.users.UserRequestDTO;
import com.luxmotors.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserRequestDTO userRequestDTO) {
        User newUser = userService.createUser(userRequestDTO);
        return ResponseEntity.ok(newUser);
    }
}
