package upc.edu.pe.hungerfoodbackend.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "money_donation")
public class MoneyDonation {
    //Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "money_donation_id")
    private Long id; //id money donation
    //Monto_total
    @Column(name = "total_amount", nullable = false)
    private Double total_amount;
    //Fecha de pago
    @Column(name = "payment_date", nullable = false)
    private LocalDate payment_date;
    //Metodo de pago
    @ManyToOne
    @JoinColumn(name = "payment_method_id", nullable = false)
    private PaymentMethod paymentMethod;
}
