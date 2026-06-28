package com.luxmotors.api.controller;

import com.luxmotors.api.domain.auth.LoginRequestDTO;
import com.luxmotors.api.domain.auth.LoginResponseDTO;
import com.luxmotors.api.domain.users.User;
import com.luxmotors.api.services.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO data) {
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(data.email(), data.senha());

        Authentication auth = authenticationManager.authenticate(authToken);
        String token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
}