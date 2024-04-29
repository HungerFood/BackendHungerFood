package com.example.demo.controller;

import com.example.demo.entities.Users;
import com.example.demo.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    @Autowired
    private PasswordEncoder bcrypt;
    @Autowired
    private IUserService userService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Users user) {
        user.setPassword(bcrypt.encode(user.getPassword()));
        return ResponseEntity.ok(userService.save(user));
    }

    @GetMapping("/donors")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Users> getDonorUsers() {
        return userService.findUsersByRole("ROLE_DONANTE");
    }
}
