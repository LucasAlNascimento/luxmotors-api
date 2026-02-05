package com.luxmotors.api.services;

import com.luxmotors.api.domain.users.User;
import com.luxmotors.api.domain.users.UserRequestDTO;
import com.luxmotors.api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createUser(UserRequestDTO data) {
        User user = User.builder()
                .nome(data.nome())
                .email(data.email())
                .senha(data.senha())
                .isAdmin(data.isAdmin())
                .dataCadastro(LocalDateTime.now())
                .build();

        return userRepository.save(user);
    }
}
