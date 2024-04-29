package com.example.demo.controller;

import com.example.demo.dto.TransactionVoucherDTO;
import com.example.demo.entities.TransactionVoucher;
import com.example.demo.service.ITransactionVoucherService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/TransactionVoucher")
@CrossOrigin
public class TransactionVoucherController {
    @Autowired
    private ITransactionVoucherService iTransactionVoucherService;

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('ROLE_DONANTE')") //only donor can save transaction voucher
    public ResponseEntity<?> save(@RequestBody TransactionVoucherDTO transactionVoucherDTO) {
        try{
            ModelMapper modelMapper = new ModelMapper();
            TransactionVoucher transactionVoucher = modelMapper.map(transactionVoucherDTO, TransactionVoucher.class);
            transactionVoucher = iTransactionVoucherService.save(transactionVoucher);
            transactionVoucherDTO = modelMapper.map(transactionVoucher, TransactionVoucherDTO.class);
            return new ResponseEntity<>(transactionVoucherDTO, HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al registrar donación de alimento");
        }
    }

    //findall
    @GetMapping("/findAll")
    @PreAuthorize("hasAuthority('ROLE_DONANTE') or hasAuthority('ROLE_ADMIN') ") //only admin can see all transaction vouchers
    public ResponseEntity<?> findAll() {
        try{
            return new ResponseEntity<>(iTransactionVoucherService.findAll(), HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al buscar donaciones de alimentos");
        }
    }

    //update
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('ROLE_DONANTE')") //only donor can update transaction voucher
    public ResponseEntity<?> update(@RequestBody TransactionVoucherDTO transactionVoucherDTO) {
        try{
            ModelMapper modelMapper = new ModelMapper();
            TransactionVoucher transactionVoucher = modelMapper.map(transactionVoucherDTO, TransactionVoucher.class);
            transactionVoucher = iTransactionVoucherService.update(transactionVoucher);
            transactionVoucherDTO = modelMapper.map(transactionVoucher, TransactionVoucherDTO.class);
            return new ResponseEntity<>(transactionVoucherDTO, HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al actualizar donación de alimento");
        }
    }

    //delete
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_DONANTE')") //only donor can delete transaction voucher
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try{
            iTransactionVoucherService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al eliminar donación de alimento");
        }
    }
}
