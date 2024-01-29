package mx.edu.utez.almacen.controller.product.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import mx.edu.utez.almacen.model.category.CategoryBean;
import mx.edu.utez.almacen.model.product.ProductBean;

@Data
@AllArgsConstructor
public class ProductDto {

    private Long id;
    private String name;
    private String trademark;
    private String model;
    private Double price;
    private CategoryBean categoryBean;

    public ProductBean toEntity(){
        return new ProductBean(name,trademark,model,price,categoryBean);
    }

    public ProductBean toEntityId(){
        return new ProductBean(id,name,trademark,model,price,categoryBean);
    }
}
