package upc.edu.pe.hungerfoodbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import upc.edu.pe.hungerfoodbackend.entities.FoodDonatedByDonor;

import java.util.List;

@Repository
public interface IFoodDonatedByDonorRepository extends JpaRepository<FoodDonatedByDonor, Long> {
    @Query("SELECT f FROM FoodDonatedByDonor f WHERE f.users.id IN (SELECT MIN(f2.users.id) FROM FoodDonatedByDonor f2 GROUP BY f2.users.id)")
    FoodDonatedByDonor findFirstByUser(Long userId);

}
