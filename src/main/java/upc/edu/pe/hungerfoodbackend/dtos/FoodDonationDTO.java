package upc.edu.pe.hungerfoodbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import upc.edu.pe.hungerfoodbackend.entities.TypeOfFood;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodDonationDTO {
    private Long id;
    private String food_name;
    private String specific_description;
    private LocalDate broadcast_date;
    private LocalDate expiration_date;
    private TypeOfFood typeOfFood;
}
