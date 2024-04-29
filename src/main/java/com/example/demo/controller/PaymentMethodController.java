package com.example.demo.controller;

import com.example.demo.dto.PaymentMethodDTO;
import com.example.demo.entities.PaymentMethod;
import com.example.demo.service.IPaymentMethodService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/PaymentMethod")
@CrossOrigin
public class PaymentMethodController {
    @Autowired
    private IPaymentMethodService iPaymentMethodService;

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')") //only admin
    public ResponseEntity<?> save(@RequestBody PaymentMethodDTO paymentMethodDTO) {
        try{
            ModelMapper modelMapper = new ModelMapper();
            PaymentMethod paymentMethod = modelMapper.map(paymentMethodDTO, PaymentMethod.class);
            paymentMethod = iPaymentMethodService.save(paymentMethod);
            paymentMethodDTO = modelMapper.map(paymentMethod, PaymentMethodDTO.class);
            return new ResponseEntity<>(paymentMethodDTO, HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al registrar donación de alimento");
        }
    }

    @GetMapping("/findAll")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')") //only admin
    public ResponseEntity<?> findAll() {
        try{
            return new ResponseEntity<>(iPaymentMethodService.findAll(), HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al obtener donaciones de alimento");
        }
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')") //only admin
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try{
            iPaymentMethodService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al eliminar donación de alimento");
        }
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')") //only admin
    public ResponseEntity<?> update(@RequestBody PaymentMethodDTO paymentMethodDTO) {
        try{
            ModelMapper modelMapper = new ModelMapper();
            PaymentMethod paymentMethod = modelMapper.map(paymentMethodDTO, PaymentMethod.class);
            paymentMethod = iPaymentMethodService.update(paymentMethod);
            paymentMethodDTO = modelMapper.map(paymentMethod, PaymentMethodDTO.class);
            return new ResponseEntity<>(paymentMethodDTO, HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al actualizar donación de alimento");
        }
    }

}
