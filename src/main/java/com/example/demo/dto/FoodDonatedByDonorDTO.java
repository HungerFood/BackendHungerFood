package com.example.demo.dto;

import com.example.demo.entities.FoodDonation;
import com.example.demo.entities.Users;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodDonatedByDonorDTO {
    private Long id;
    private LocalDate donationdate;
    private Long quantityFood;
    private Users users;
    private FoodDonation foodDonation;
}
