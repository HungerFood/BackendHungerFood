package upc.edu.pe.hungerfoodbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import upc.edu.pe.hungerfoodbackend.entities.PaymentMethod;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoneyDonationDTO {
    private Long id;
    private Double total_amount;
    private LocalDate payment_date;
    private PaymentMethod paymentMethod;
}
