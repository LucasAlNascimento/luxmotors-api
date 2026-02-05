package com.luxmotors.api.domain.users;

public record UserRequestDTO(
        String nome,
        String email,
        String senha,
        boolean isAdmin) {
}
