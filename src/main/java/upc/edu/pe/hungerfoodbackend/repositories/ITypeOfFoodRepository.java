package upc.edu.pe.hungerfoodbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import upc.edu.pe.hungerfoodbackend.entities.TypeOfFood;
import upc.edu.pe.hungerfoodbackend.entities.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface ITypeOfFoodRepository extends JpaRepository<TypeOfFood, Long> {

}
