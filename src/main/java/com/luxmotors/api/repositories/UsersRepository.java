package com.luxmotors.api.repositories;

import com.luxmotors.api.domain.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsersRepository extends JpaRepository<Users, UUID> {
}
