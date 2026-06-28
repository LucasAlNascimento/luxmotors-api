package com.luxmotors.api.domain.users;

import java.util.UUID;

public record UserResponseDTO(
        UUID id,
        String nome,
        String email
) {}
