package upc.edu.pe.hungerfoodbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SumTransactionVoucherDTO {
    private Double total;
    private String userName;
}
