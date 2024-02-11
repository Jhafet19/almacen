package mx.edu.utez.almacen.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.almacen.model.category.CategoryBean;
import mx.edu.utez.almacen.model.inventory.InventoryBean;
import mx.edu.utez.almacen.model.rol.RolBean;

import java.time.LocalDate;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ProductBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    private String name;
    @Column(length = 50, nullable = false)
    private String trademark;
    @Column(length = 50, nullable = false)
    private String model;
    @Column(nullable = false)
    private Double price;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryBean categoryBean;

    @OneToOne(mappedBy = "productBean", fetch = FetchType.EAGER)
    @JsonManagedReference
    private InventoryBean inventoryBean;

    public ProductBean(String name, String trademark, String model, Double price, CategoryBean categoryBean) {
        this.name = name;
        this.trademark = trademark;
        this.model = model;
        this.price = price;
        this.categoryBean = categoryBean;
    }

    public ProductBean(Long id, String name, String trademark, String model, Double price, CategoryBean categoryBean) {
        this.id = id;
        this.name = name;
        this.trademark = trademark;
        this.model = model;
        this.price = price;
        this.categoryBean = categoryBean;
    }
    public ProductBean(String name, String trademark, String model, Double price) {
        this.name = name;
        this.trademark = trademark;
        this.model = model;
        this.price = price;
    }
}
