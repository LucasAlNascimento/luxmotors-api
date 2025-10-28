package com.luxmotors.api.services;

import com.luxmotors.api.domain.users.Users;
import com.luxmotors.api.domain.users.UsersRequestDTO;
import com.luxmotors.api.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UsersService {

    @Autowired
    UsersRepository usersRepository;

    public Users createUser(UsersRequestDTO data) {
        Users user = new Users();

        user.setNome(data.nome());
        user.setEmail(data.email());
        user.setSenha(data.senha());
        user.setIsAdmin(data.isAdmin());
        user.setDataCadastro(new Date());

        return usersRepository.save(user);
    }
}
