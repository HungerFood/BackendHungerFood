package com.example.demo.dto;

import com.example.demo.entities.TransactionVoucher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDetailDTO {
    private Long id;
    private TransactionVoucher transactionVoucher;
}
