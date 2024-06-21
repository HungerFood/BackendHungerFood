package upc.edu.pe.hungerfoodbackend.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import upc.edu.pe.hungerfoodbackend.dtos.MoneyDonationDTO;
import upc.edu.pe.hungerfoodbackend.entities.MoneyDonation;
import upc.edu.pe.hungerfoodbackend.iservices.IUserService;
import upc.edu.pe.hungerfoodbackend.iservices.iMoneyDonationService;

@CrossOrigin(origins = {"http://localhost:4200", "http://18.216.165.101:6868"})

@RestController
@RequestMapping("/api")
public class MoneyDonationController {
    @Autowired
    private iMoneyDonationService iMoneyDonationService1;

    @Autowired
    private IUserService iUserService;

    @PostMapping("/MoneyDonation/save") //localhost:8080/api/MoneyDonation/save
    @PreAuthorize("hasAuthority('DONANTE')") //only DONANTE can save
    public ResponseEntity<?> save(@RequestBody MoneyDonationDTO moneyDonationDTO) {
        try{
            ModelMapper modelMapper = new ModelMapper();
            MoneyDonation moneyDonation = modelMapper.map(moneyDonationDTO, MoneyDonation.class);
            moneyDonation = iMoneyDonationService1.save(moneyDonation);
            moneyDonationDTO = modelMapper.map(moneyDonation, MoneyDonationDTO.class);
            return new ResponseEntity<>(moneyDonationDTO, HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al registrar donación de dinero");
        }
    }

    @GetMapping("/MoneyDonation/findAll") //localhost:8080/api/MoneyDonation/findAll
    @PreAuthorize("hasAuthority('DONANTE') or hasAnyAuthority('ADMIN')" ) //only admin can see all
    public ResponseEntity<?> findAll() {
        try{
            return new ResponseEntity<>(iMoneyDonationService1.findAll(), HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al buscar donaciones de dinero");
        }
    }

    @PutMapping("/MoneyDonation/update") //localhost:8080/api/MOneyDonation/update
    @PreAuthorize("hasAuthority('DONANTE')") //only admin can update
    public ResponseEntity<?> update(@RequestBody MoneyDonationDTO moneyDonationDTO) {
        try{
            ModelMapper modelMapper = new ModelMapper();
            MoneyDonation moneyDonation = modelMapper.map(moneyDonationDTO, MoneyDonation.class);
            moneyDonation = iMoneyDonationService1.update(moneyDonation);
            moneyDonationDTO = modelMapper.map(moneyDonation, MoneyDonationDTO.class);
            return new ResponseEntity<>(moneyDonationDTO, HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al actualizar donación de dinero");
        }
    }

    @DeleteMapping("/MoneyDonation/delete/{id}") //localhost:8080/api/MoneyDonation/delete/1
    @PreAuthorize("hasAuthority('DONANTE')") //only admin can delete
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try{
            iMoneyDonationService1.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al eliminar donación de dinero");
        }
    }

    //listar por id
    @GetMapping("/MoneyDonation/findById/{id}") //localhost:8080/api/MoneyDonation/findById/1
    @PreAuthorize("hasAuthority('DONANTE') or hasAnyAuthority('ADMIN')") //only admin can see all
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try{
            return new ResponseEntity<>(iMoneyDonationService1.findById(id), HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error al buscar donación de dinero");
        }
    }
}
