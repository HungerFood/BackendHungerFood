package com.example.demo.service;

import com.example.demo.entities.TypeOfFood;
import com.example.demo.entities.Users;

import java.util.List;

public interface ITypeOfFoodService {
    //save
    public TypeOfFood save(TypeOfFood typeOfFood);

    //delete
    public void delete(Long id);

    //update
    public TypeOfFood update(TypeOfFood typeOfFood);

    //findall
    public List<TypeOfFood> findAll();
}
