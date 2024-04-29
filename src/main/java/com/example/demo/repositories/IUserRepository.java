package com.example.demo.repositories;

import com.example.demo.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<Users, Long> {
    public Users findByUsername(String username);

    @Query("SELECT u FROM Users u JOIN u.roles r WHERE r.rol = ?1")
    List<Users> findByRole(String roleName);
}
