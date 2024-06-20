package upc.edu.pe.hungerfoodbackend.iservices.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import upc.edu.pe.hungerfoodbackend.entities.User;
import upc.edu.pe.hungerfoodbackend.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repo;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repo.findByEmail(email);
        if(user == null) {
            throw new UsernameNotFoundException(String.format("User not exists", email));
        }
        List<GrantedAuthority> roles = new ArrayList<>();
        user.getRoles().forEach(rol -> {
           roles.add(new SimpleGrantedAuthority((rol.getNameRole().name())));
        });
        UserDetails ud = new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.getEnabled(), true, true, true, roles);
        return ud;
    }
}