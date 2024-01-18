package mx.edu.utez.almacen.model.category;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.almacen.model.product.ProductBean;

import java.util.Set;

//Creamos uns antidad
@Entity
//Le ponemos nombre
@Table(name="category")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryBean {
    //Colocamos la llave foranea
   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 40, nullable = false)
    private String nombre;

    @Column(length = 10)
    private  String codigo;

    @OneToMany(mappedBy = "categoryBean")
    private Set<ProductBean> productBean;


}
