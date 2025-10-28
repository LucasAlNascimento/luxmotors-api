package com.luxmotors.api.domain.users;

public record UsersRequestDTO(
        String nome,
        String email,
        String senha,
        Boolean isAdmin) {
}
