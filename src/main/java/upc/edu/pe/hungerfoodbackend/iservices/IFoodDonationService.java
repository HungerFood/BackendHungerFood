package upc.edu.pe.hungerfoodbackend.iservices;

import upc.edu.pe.hungerfoodbackend.entities.FoodDonation;

import java.util.List;

public interface IFoodDonationService {
    //save
    public FoodDonation save(FoodDonation foodDonation);

    //delete
    public void delete(Long id);

    //findall
    public List<FoodDonation> findall();

    //update
    public FoodDonation update(FoodDonation foodDonation);

    //findbyid
    public FoodDonation findById(Long id);

}
