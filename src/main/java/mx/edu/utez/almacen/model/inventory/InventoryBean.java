package mx.edu.utez.almacen.model.inventory;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @JsonBackReference
    private ProductBean productBean;

    @OneToMany(mappedBy = "inventoryBean")
    private Set<ProductSale> productSales;

    public InventoryBean(LocalDate expiration, Integer stock, Double pricePublic) {
        this.expiration = expiration;
        this.stock = stock;
        this.pricePublic = pricePublic;
    }

    public InventoryBean(Long id, LocalDate expiration, Integer stock, Double pricePublic) {
        this.id = id;
        this.expiration = expiration;
        this.stock = stock;
        this.pricePublic = pricePublic;
    }
}
