package mx.edu.utez.almacen.model.inventory;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.almacen.model.product.ProductBean;
import mx.edu.utez.almacen.model.saleproduct.ProductSale;

import java.time.LocalDate;
import java.util.Set;


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

    @OneToOne
    @JoinColumn(name = "product_id")
    private ProductBean productBean;

    @OneToMany(mappedBy = "inventoryBean")
    private Set<ProductSale> productSales;

}
