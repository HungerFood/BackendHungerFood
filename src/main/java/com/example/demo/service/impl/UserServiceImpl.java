package com.example.demo.service.impl;

import com.example.demo.entities.Users;
import com.example.demo.repositories.IUserRepository;
import com.example.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserRepository iUserRepository;
    @Override
    public Users save(Users user) {
        return iUserRepository.save(user);//guardar usuario
    }

    @Override
    public List<Users> findUsersByRole(String roleName) {
        return iUserRepository.findByRole(roleName);
    }
}
