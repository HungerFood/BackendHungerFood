package upc.edu.pe.hungerfoodbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import upc.edu.pe.hungerfoodbackend.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
    Role findByNameRole(Role.NameRole name);

}
