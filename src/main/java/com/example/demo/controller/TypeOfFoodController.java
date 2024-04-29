package com.example.demo.controller;

import com.example.demo.dto.TypeOfFoodDTO;
import com.example.demo.entities.TypeOfFood;
import com.example.demo.service.ITypeOfFoodService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/TypeOfFood")
@CrossOrigin
public class TypeOfFoodController {
    @Autowired
    private ITypeOfFoodService iTypeOfFoodService;

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')") //only admin can save
    public ResponseEntity<?> save(@RequestBody TypeOfFoodDTO typeOfFoodDTO) {
        try{
            ModelMapper modelMapper = new ModelMapper();
            TypeOfFood typeOfFood = modelMapper.map(typeOfFoodDTO, TypeOfFood.class);
            typeOfFood = iTypeOfFoodService.save(typeOfFood);
            typeOfFoodDTO = modelMapper.map(typeOfFood, TypeOfFoodDTO.class);
            return new ResponseEntity<>(typeOfFoodDTO, HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al registrar donación de alimento");
        }
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')") //only admin can delete
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try{
            iTypeOfFoodService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al eliminar donación de alimento");
        }
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')") //only admin can update
    public ResponseEntity<?> update(@RequestBody TypeOfFoodDTO typeOfFoodDTO) {
        try{
            ModelMapper modelMapper = new ModelMapper();
            TypeOfFood typeOfFood = modelMapper.map(typeOfFoodDTO, TypeOfFood.class);
            typeOfFood = iTypeOfFoodService.update(typeOfFood);
            typeOfFoodDTO = modelMapper.map(typeOfFood, TypeOfFoodDTO.class);
            return new ResponseEntity<>(typeOfFoodDTO, HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al actualizar donación de alimento");
        }
    }

    @GetMapping("/findAll")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')") //only admin can find all
    public ResponseEntity<?> findAll() {
        try{
            return new ResponseEntity<>(iTypeOfFoodService.findAll(), HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al buscar donaciones de alimento");
        }
    }
}
