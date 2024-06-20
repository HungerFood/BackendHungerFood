package upc.edu.pe.hungerfoodbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import upc.edu.pe.hungerfoodbackend.entities.PaymentMethod;
import upc.edu.pe.hungerfoodbackend.entities.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionVoucherDTO {
    private Long id;
    private Double total_amount;
    private User user;
    private PaymentMethod paymentMethod;
}
