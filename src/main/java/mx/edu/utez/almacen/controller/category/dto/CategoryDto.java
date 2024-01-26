package mx.edu.utez.almacen.controller.category.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import mx.edu.utez.almacen.model.category.CategoryBean;

@AllArgsConstructor
@Data
public class CategoryDto {

    private Long id;

    private String nombre;

    private  String codigo;

    public CategoryBean toEntity(){
        return new CategoryBean(nombre,codigo);
    }
}
