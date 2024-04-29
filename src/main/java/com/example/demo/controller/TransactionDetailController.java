package com.example.demo.controller;

import com.example.demo.dto.PaymentMethodDTO;
import com.example.demo.dto.TransactionDetailDTO;
import com.example.demo.entities.PaymentMethod;
import com.example.demo.entities.TransactionDetail;
import com.example.demo.service.IPaymentMethodService;
import com.example.demo.service.ITransactionDetailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/TransactionDetail")
@CrossOrigin
public class TransactionDetailController {
    @Autowired
    private ITransactionDetailService iTransactionDetailService;



    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')") //only admin can delete
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try{
            iTransactionDetailService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al eliminar donación de alimento");
        }
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')") //only admin can get all
    public ResponseEntity<?> getAll() {
        try{
            return new ResponseEntity<>(iTransactionDetailService.getAll(), HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al obtener donaciones de alimentos");
        }
    }


}
