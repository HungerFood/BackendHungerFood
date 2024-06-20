package upc.edu.pe.hungerfoodbackend.iservices.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.edu.pe.hungerfoodbackend.entities.FoodDonation;
import upc.edu.pe.hungerfoodbackend.iservices.IFoodDonationService;
import upc.edu.pe.hungerfoodbackend.repositories.IFoodDonationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FoodDonationServiceImpl implements IFoodDonationService {
    @Autowired
    private IFoodDonationRepository iFoodDonationRepository;

    @Override
    public FoodDonation save(FoodDonation foodDonation) {
        return iFoodDonationRepository.save(foodDonation);
    }

    @Override
    public void delete(Long id) {
        iFoodDonationRepository.deleteById(id);
    }

    @Override
    public List<FoodDonation> findall() {
        return iFoodDonationRepository.findAll();
    }

    @Override
    public FoodDonation update(FoodDonation foodDonation) {
        Optional<FoodDonation> foodDonationOptional = iFoodDonationRepository.findById(foodDonation.getId());
        if (foodDonationOptional.isPresent()) {
            FoodDonation updatedFoodDonation = foodDonationOptional.get();
            updatedFoodDonation.setFood_name(foodDonation.getFood_name());
            updatedFoodDonation.setSpecific_description(foodDonation.getSpecific_description());
            updatedFoodDonation.setBroadcast_date(foodDonation.getBroadcast_date());
            updatedFoodDonation.setExpiration_date(foodDonation.getExpiration_date());
            updatedFoodDonation.setTypeOfFood(foodDonation.getTypeOfFood());
            return iFoodDonationRepository.save(updatedFoodDonation);
        } else {
            throw new RuntimeException("FoodDonation not found with id: " + foodDonation.getId());
        }
    }

    @Override
    public FoodDonation findById(Long id) {
        Optional<FoodDonation> foodDonationOptional = iFoodDonationRepository.findById(id);
        if (foodDonationOptional.isPresent()) {
            return foodDonationOptional.get();
        } else {
            throw new RuntimeException("FoodDonation not found with id: " + id);
        }
    }



}
