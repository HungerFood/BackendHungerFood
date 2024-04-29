package com.example.demo.controller;


import com.example.demo.dto.FoodDonationDTO;
import com.example.demo.entities.FoodDonation;
import com.example.demo.service.IFoodDonationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("/FoodDonation")
@CrossOrigin
public class FoodDonationController {
    @Autowired
    private IFoodDonationService iFoodDonationService;

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')") //only admin can save
         public ResponseEntity<?> save(@RequestBody FoodDonationDTO foodDonationDTO) {
        try{
            ModelMapper modelMapper = new ModelMapper();
            FoodDonation foodDonation = modelMapper.map(foodDonationDTO, FoodDonation.class);
            foodDonation = iFoodDonationService.save(foodDonation);
            foodDonationDTO = modelMapper.map(foodDonation, FoodDonationDTO.class);
            return new ResponseEntity<>(foodDonationDTO, HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al registrar donación de alimento");
        }
    }

    @GetMapping("/findAll")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')") //only admin can see all
    public ResponseEntity<?> findAll() {
        try{
            return new ResponseEntity<>(iFoodDonationService.findall(), HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al buscar donaciones de alimentos");
        }
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')") //only admin can update
    public ResponseEntity<?> update(@RequestBody FoodDonationDTO foodDonationDTO) {
        try{
            ModelMapper modelMapper = new ModelMapper();
            FoodDonation foodDonation = modelMapper.map(foodDonationDTO, FoodDonation.class);
            foodDonation = iFoodDonationService.update(foodDonation);
            foodDonationDTO = modelMapper.map(foodDonation, FoodDonationDTO.class);
            return new ResponseEntity<>(foodDonationDTO, HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al actualizar donación de alimento");
        }
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')") //only admin can delete
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try{
            iFoodDonationService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al eliminar donación de alimento");
        }
    }
}
