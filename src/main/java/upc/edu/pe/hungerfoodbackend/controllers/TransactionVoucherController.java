package upc.edu.pe.hungerfoodbackend.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import upc.edu.pe.hungerfoodbackend.dtos.TransactionVoucherDTO;
import upc.edu.pe.hungerfoodbackend.entities.TransactionVoucher;
import upc.edu.pe.hungerfoodbackend.entities.User;
import upc.edu.pe.hungerfoodbackend.iservices.ITransactionVoucherService;
import upc.edu.pe.hungerfoodbackend.iservices.IUserService;

import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:4200", "http://18.216.165.101:6868"})

@RestController
@RequestMapping("/api")
public class TransactionVoucherController {
    @Autowired
    private ITransactionVoucherService iTransactionVoucherService;

    @Autowired
    private IUserService iUserService;

    @PostMapping("/TransactionVoucher/save") //localhost:8080/api/TransactionVoucher/save
    @PreAuthorize("hasAuthority('DONANTE')") //only donor can save transaction voucher
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
    @GetMapping("/TransactionVoucher/findAll") //localhost:8080/api/TransactionVoucher/findAll
    @PreAuthorize("hasAuthority('DONANTE') or hasAuthority('ADMIN') ") //only admin can see all transaction vouchers
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
    @PutMapping("/TransactionVoucher/update") //localhost:8080/api/TransactionVoucher/update
    @PreAuthorize("hasAuthority('DONANTE')") //only donor can update transaction voucher
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
    @DeleteMapping("/TransactionVoucher/delete/{id}") //localhost:8080/api/TransactionVoucher/delete/1
    @PreAuthorize("hasAuthority('DONANTE')") //only donor can delete transaction voucher
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

    //find by id
    @GetMapping("/TransactionVoucher/firstByUser/{userId}") //localhost:8080/api/TransactionVoucher/firstByUser/1
    @PreAuthorize("hasAuthority('ADMIN') ") //only donor or admin can see the first transaction voucher by user
    public ResponseEntity<?> findFirstByUser(@PathVariable Long userId) {
        try{
            Optional<User> user = iUserService.findById(userId);
            Optional<TransactionVoucher> transactionVoucher = iTransactionVoucherService.findFirstByUser(user);
            if(transactionVoucher.isPresent()){
                ModelMapper modelMapper = new ModelMapper();
                TransactionVoucherDTO transactionVoucherDTO = modelMapper.map(transactionVoucher.get(), TransactionVoucherDTO.class);
                return new ResponseEntity<>(transactionVoucherDTO, HttpStatus.OK);
            } else {
                // Mensaje personalizado para el frontend
                return new ResponseEntity<>("No se encontró ninguna donación de alimento para el usuario con id: " + userId, HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e){
            e.printStackTrace();
            // Mensaje personalizado para el backend
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al buscar la primera donación de alimento para el usuario con id: " + userId, e);
        }
    }

}
