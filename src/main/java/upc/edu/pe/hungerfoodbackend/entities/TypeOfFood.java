package upc.edu.pe.hungerfoodbackend.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "type_of_food")
public class TypeOfFood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_of_food_id")
    private Long id;
    @Column(name = "food_type_name", nullable = false)
    private String food_type_name;


}
