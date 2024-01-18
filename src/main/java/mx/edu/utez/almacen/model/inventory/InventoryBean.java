package mx.edu.utez.almacen.model.inventory;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Entity
//Le ponemos nombre
@Table(name="inventory")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class InventoryBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(columnDefinition = "DATE",nullable = false)
    private LocalDate expiration;

    @Column(nullable = false)
    private  Integer stock;

    @Column(nullable = false)
    private  Double pricePublic;


}
