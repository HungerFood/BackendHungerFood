package upc.edu.pe.hungerfoodbackend.iservices;

import upc.edu.pe.hungerfoodbackend.entities.FoodDonatedByDonor;

import java.util.List;

public interface IFoodDonatedByDonorService {
    //save
    public FoodDonatedByDonor save(FoodDonatedByDonor foodDonatedByDonor);
    //findAll
    List<FoodDonatedByDonor> findAll();
    //list by id
    FoodDonatedByDonor findFirstByUser(Long userId);
    //update
    public FoodDonatedByDonor update(FoodDonatedByDonor foodDonatedByDonor);
    //delete
    public void delete(Long id);

}
