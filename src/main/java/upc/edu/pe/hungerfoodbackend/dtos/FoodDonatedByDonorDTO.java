package upc.edu.pe.hungerfoodbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import upc.edu.pe.hungerfoodbackend.entities.FoodDonation;
import upc.edu.pe.hungerfoodbackend.entities.User;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodDonatedByDonorDTO {
    private Long id;
    private LocalDate donationdate;
    private Long quantityFood;
    private User users;
    private FoodDonation foodDonation;
}
