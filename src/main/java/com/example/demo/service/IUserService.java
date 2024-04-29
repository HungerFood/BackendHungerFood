package com.example.demo.service;

import com.example.demo.entities.Users;

import java.util.List;

public interface IUserService {
    //save
    public Users save(Users user);

    List<Users> findUsersByRole(String roleName);
}
