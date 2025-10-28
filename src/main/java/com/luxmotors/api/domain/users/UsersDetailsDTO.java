package com.luxmotors.api.domain.users;

import java.util.Date;
import java.util.UUID;

public record UsersDetailsDTO(
        UUID id,
        String nome,
        String email,
        String senha,
        Boolean isAdmin,
        Date dataCadastro) {
}
