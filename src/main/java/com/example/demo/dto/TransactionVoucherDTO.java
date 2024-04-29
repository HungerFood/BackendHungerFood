package com.example.demo.dto;

import com.example.demo.entities.PaymentMethod;
import com.example.demo.entities.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionVoucherDTO {
    private Long id;
    private Double total_amount;
    private Users user;
    private PaymentMethod paymentMethod;
}
