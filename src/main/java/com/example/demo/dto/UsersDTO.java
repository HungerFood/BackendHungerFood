package com.example.demo.dto;

import com.example.demo.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password;
    private boolean enabled;
    private Role role;
}
