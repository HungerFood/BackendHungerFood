package upc.edu.pe.hungerfoodbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import upc.edu.pe.hungerfoodbackend.entities.TransactionVoucher;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDetailDTO {
    private Long id;
    private TransactionVoucher transactionVoucher;
}
