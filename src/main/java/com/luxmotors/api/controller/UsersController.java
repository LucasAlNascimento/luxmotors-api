package com.luxmotors.api.controller;

import com.luxmotors.api.domain.users.Users;
import com.luxmotors.api.domain.users.UsersRequestDTO;
import com.luxmotors.api.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody UsersRequestDTO usersRequestDTO) {
        Users newUser = usersService.createUser(usersRequestDTO);
        return ResponseEntity.ok(newUser);
    }
}
