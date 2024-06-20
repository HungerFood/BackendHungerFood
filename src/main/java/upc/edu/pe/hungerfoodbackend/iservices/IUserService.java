package upc.edu.pe.hungerfoodbackend.iservices;

import upc.edu.pe.hungerfoodbackend.dtos.response.JwtResponse;
import upc.edu.pe.hungerfoodbackend.entities.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    User insert(User user);
    List<User> list();
    User findByEmail(String email);
    Boolean existsByEmail(String email);
    User update(User user) throws Exception;
    User searchId(Long id) throws Exception;
    void delete(Long id) throws Exception;
    JwtResponse login(String email, String password);
    void authenticate(String username, String password) throws Exception;
    List<User>findByRoleDonante();

    Optional<User> findById(Long id);

    Optional<User> findDonorById(Long id);
}
