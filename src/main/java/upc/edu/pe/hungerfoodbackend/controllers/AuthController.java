package upc.edu.pe.hungerfoodbackend.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upc.edu.pe.hungerfoodbackend.dtos.UserDTO;
import upc.edu.pe.hungerfoodbackend.dtos.request.LoginUserRequestDTO;
import upc.edu.pe.hungerfoodbackend.dtos.request.RegisterUserRequestDTO;
import upc.edu.pe.hungerfoodbackend.dtos.response.JwtResponse;
import upc.edu.pe.hungerfoodbackend.entities.User;
import upc.edu.pe.hungerfoodbackend.iservices.IUserService;
import upc.edu.pe.hungerfoodbackend.util.DTOConverter;

//Registrar y loguear usuarios
@CrossOrigin(origins = {"http://localhost:4200", "http://18.216.165.101:6868", }, exposedHeaders = "Authorization")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private IUserService userService;
    @Autowired
    private DTOConverter dtoConverter;
    @PostMapping("/register") //http://localhost:8080/api/auth/register
    public ResponseEntity<UserDTO> register(@Valid @RequestBody RegisterUserRequestDTO request) {
        User user = dtoConverter.convertToEntity(request, User.class);
        user = userService.insert(user);
        UserDTO userDTO = dtoConverter.convertToDto(user, UserDTO.class);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PostMapping("/login")  //http://localhost:8080/api/auth/login
    public ResponseEntity<?> login(@Valid @RequestBody LoginUserRequestDTO request) {
        JwtResponse jwtResponse = userService.login(request.getEmail(), request.getPassword());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Authorization", jwtResponse.getJwttoken());
        return ResponseEntity.ok().headers(responseHeaders).body(jwtResponse);
    }
}
