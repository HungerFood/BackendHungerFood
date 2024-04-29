package com.example.demo.controller;

import com.example.demo.dto.FoodDonatedByDonorDTO;
import com.example.demo.dto.FoodDonationDTO;
import com.example.demo.entities.FoodDonatedByDonor;
import com.example.demo.entities.FoodDonation;
import com.example.demo.service.IFoodDonatedByDonorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/FoodDonatedByDonor")
@CrossOrigin
public class FoodDonatedByDonorController {
    @Autowired
    private IFoodDonatedByDonorService iFoodDonatedByDonorService;

    //save donor food
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('ROLE_DONANTE')") //only donor
    public ResponseEntity<?> save(@RequestBody FoodDonatedByDonorDTO foodDonatedByDonorDTO) {
        try{
            ModelMapper modelMapper = new ModelMapper();
            FoodDonatedByDonor foodDonatedByDonor = modelMapper.map(foodDonatedByDonorDTO, FoodDonatedByDonor.class);
            foodDonatedByDonor = iFoodDonatedByDonorService.save(foodDonatedByDonor);
            foodDonatedByDonorDTO = modelMapper.map(foodDonatedByDonor, FoodDonatedByDonorDTO.class);
            return new ResponseEntity<>(foodDonatedByDonorDTO, HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al registrar donación de alimento");
        }
    }

    //listar
    @GetMapping("/findAll")
    @PreAuthorize("hasAuthority('ROLE_DONANTE') or hasAuthority('ROLE_ADMIN')") //donor and admin
    public ResponseEntity<?> findAll() {
        ModelMapper modelMapper = new ModelMapper();
        List<FoodDonatedByDonorDTO> list = Arrays.asList(modelMapper.map(iFoodDonatedByDonorService.findAll(),
                FoodDonatedByDonorDTO[].class));
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    //update
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('ROLE_DONANTE')") //only donor
    public ResponseEntity<?> update(@RequestBody FoodDonatedByDonorDTO foodDonatedByDonorDTO) {
        try{
            ModelMapper modelMapper = new ModelMapper();
            FoodDonatedByDonor foodDonatedByDonor = modelMapper.map(foodDonatedByDonorDTO, FoodDonatedByDonor.class);
            foodDonatedByDonor = iFoodDonatedByDonorService.update(foodDonatedByDonor);
            foodDonatedByDonorDTO = modelMapper.map(foodDonatedByDonor, FoodDonatedByDonorDTO.class);
            return new ResponseEntity<>(foodDonatedByDonorDTO, HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al actualizar donación de alimento");
        }
    }

    //delete
    @DeleteMapping("/delete/{food_donated_by_donor_id}")
    public ResponseEntity<?> delete(@PathVariable("food_donated_by_donor_id") Long id) {
        try {
            iFoodDonatedByDonorService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al eliminar donación de alimento");
        }
    }
}
