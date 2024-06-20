package upc.edu.pe.hungerfoodbackend.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import upc.edu.pe.hungerfoodbackend.dtos.PaymentMethodDTO;
import upc.edu.pe.hungerfoodbackend.entities.PaymentMethod;
import upc.edu.pe.hungerfoodbackend.iservices.IPaymentMethodService;

@CrossOrigin(origins = {"http://localhost:4200", "http://18.216.165.101:6868"})

@RestController
@RequestMapping("/api")
public class PaymentMethodController {
    @Autowired
    private IPaymentMethodService iPaymentMethodService;

    @PostMapping("/PaymentMethod/save") //localhost:8080/api/PaymentMethod/savez
    @PreAuthorize("hasAuthority('ADMIN')") //only admin
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

    @GetMapping("/PaymentMethod/findAll")
    @PreAuthorize("hasAuthority('ADMIN')") //only admin
    public ResponseEntity<?> findAll() {
        try{
            return new ResponseEntity<>(iPaymentMethodService.findAll(), HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al obtener donaciones de alimento");
        }
    }

    @DeleteMapping("/PaymentMethod/delete/{id}") //localhost:8080/api/PaymentMethod/delete/1
    @PreAuthorize("hasAuthority('ADMIN')") //only admin
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

    @PutMapping("/PaymentMethod/update") //localhost:8080/api/PaymentMethod/update
    @PreAuthorize("hasAuthority('ADMIN')") //only admin
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
