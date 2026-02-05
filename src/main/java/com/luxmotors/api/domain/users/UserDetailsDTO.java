package com.luxmotors.api.domain.users;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserDetailsDTO(
        UUID id,
        String nome,
        String email,
        String senha,
        boolean isAdmin,
        LocalDateTime dataCadastro) {
}
