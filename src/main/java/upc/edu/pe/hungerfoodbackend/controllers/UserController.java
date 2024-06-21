package upc.edu.pe.hungerfoodbackend.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import upc.edu.pe.hungerfoodbackend.dtos.UserDTO;
import upc.edu.pe.hungerfoodbackend.entities.User;
import upc.edu.pe.hungerfoodbackend.iservices.IUserService;
import upc.edu.pe.hungerfoodbackend.util.DTOConverter;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200", "http://18.216.165.101:6868"})
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private DTOConverter dtoConverter;

    //registrar usuario
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/user")
    public ResponseEntity<UserDTO> insert(@Valid @RequestBody UserDTO userDTO) {
        User user = dtoConverter.convertToEntity(userDTO, User.class);
        user = userService.insert(user);
        userDTO = dtoConverter.convertToDto(user, UserDTO.class);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }


    // SOLO LOS USUARIOS CON ROL ADMIN PUEDEN ACCEDER A ESTOS ENDPOINTS
    @PreAuthorize("hasAuthority('ADMIN')")
    //para visualizar todos los usuarios
    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> list() {
        List<User> users = userService.list();
        List<UserDTO> userDTOs = users.stream().map(user -> dtoConverter.convertToDto(user, UserDTO.class)).toList();
        return new ResponseEntity<>(userDTOs, HttpStatus.OK);
    }


    //para visualizar todos los usurios con rol donante por id
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/users/donante/{id}")
    public ResponseEntity<UserDTO> findDonanteById(@PathVariable Long id) throws Exception {
        User user = userService.findDonorById(id).orElseThrow(() -> new Exception("User not found"));
        UserDTO userDTO = dtoConverter.convertToDto(user, UserDTO.class);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('DONANTE') or hasAuthority('ADMIN')")
    @PutMapping("/user")
    public ResponseEntity<UserDTO> update(@Valid @RequestBody UserDTO userDTO) throws Exception {
        User user = dtoConverter.convertToEntity(userDTO, User.class);
        user = userService.update(user);
        userDTO = dtoConverter.convertToDto(user, UserDTO.class);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws Exception {
        userService.delete(id);
        return new ResponseEntity<>("User deleted", HttpStatus.OK);
    }
}
