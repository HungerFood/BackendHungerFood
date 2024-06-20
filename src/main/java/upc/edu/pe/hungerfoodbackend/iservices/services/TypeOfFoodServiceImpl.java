package upc.edu.pe.hungerfoodbackend.iservices.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.edu.pe.hungerfoodbackend.entities.TypeOfFood;
import upc.edu.pe.hungerfoodbackend.iservices.ITypeOfFoodService;
import upc.edu.pe.hungerfoodbackend.repositories.ITypeOfFoodRepository;
import java.util.List;
import java.util.Optional;

@Service
public class TypeOfFoodServiceImpl implements ITypeOfFoodService {
    @Autowired
    private ITypeOfFoodRepository iTypeOfFoodRepository;

    @Override
    public TypeOfFood save(TypeOfFood typeOfFood) {
        return iTypeOfFoodRepository.save(typeOfFood);
    }

    @Override
    public void delete(Long id) {
        iTypeOfFoodRepository.deleteById(id);
    }
    //update
    @Override
    public TypeOfFood update(TypeOfFood typeOfFood) {
        Optional<TypeOfFood> existingTypeOfFood = iTypeOfFoodRepository.findById(typeOfFood.getId());
        if (existingTypeOfFood.isPresent()) {
            TypeOfFood updatedTypeOfFood = existingTypeOfFood.get();
            updatedTypeOfFood.setFood_type_name(typeOfFood.getFood_type_name());
            //updatedTypeOfFood.setGeneral_description(typeOfFood.getGeneral_description());
            return iTypeOfFoodRepository.save(updatedTypeOfFood);
        } else {
            throw new RuntimeException("TypeOfFood not found with id: " + typeOfFood.getId());
        }
    }

    //findall
    @Override
    public List<TypeOfFood> findAll() {
        return iTypeOfFoodRepository.findAll();
    }

    @Override
    public Optional<TypeOfFood> findById(Long id) {
        return iTypeOfFoodRepository.findById(id);
    }
}
