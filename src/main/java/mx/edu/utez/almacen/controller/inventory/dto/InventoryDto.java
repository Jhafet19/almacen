package mx.edu.utez.almacen.controller.inventory.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import mx.edu.utez.almacen.model.inventory.InventoryBean;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class InventoryDto {

    private  Long id;

    private LocalDate expiration;

    private  Integer stock;

    private  Double pricePublic;

    public InventoryBean toEntity(){
        return  new InventoryBean(expiration,stock,pricePublic);
    }

    public InventoryBean toEntityId(){
        return  new InventoryBean(id,expiration,stock,pricePublic);
    }
}
