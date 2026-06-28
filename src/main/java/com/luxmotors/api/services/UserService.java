package com.luxmotors.api.services;

import com.luxmotors.api.domain.users.User;
import com.luxmotors.api.domain.users.UserRequestDTO;
import com.luxmotors.api.domain.users.UserResponseDTO;
import com.luxmotors.api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDTO createUser(UserRequestDTO data) {
        User user = User.builder()
                .nome(data.nome())
                .email(data.email())
                .senha(passwordEncoder.encode(data.senha()))
                .isAdmin(data.isAdmin())
                .dataCadastro(LocalDateTime.now())
                .build();

        User saved = userRepository.save(user);
        return new UserResponseDTO(saved.getId(), saved.getNome(), saved.getEmail());
    }
}