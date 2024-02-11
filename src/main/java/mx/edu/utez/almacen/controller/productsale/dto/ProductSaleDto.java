package mx.edu.utez.almacen.controller.productsale.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import mx.edu.utez.almacen.model.inventory.InventoryBean;
import mx.edu.utez.almacen.model.saleproduct.ProductSale;
import mx.edu.utez.almacen.model.ticket.TicketBean;

@Data
@AllArgsConstructor
public class ProductSaleDto {
    private  Long id;

    private Integer quantity;

    private Double total;

    private TicketBean ticketBean;

    private InventoryBean inventoryBean;

//    public ProductSale toEntity(){
//        return new ProductSale(quantity,total,ticketBean,inventoryBean);
//    }
//
//    public ProductSale toEntityId(){
//        return new ProductSale(id,quantity,total,ticketBean,inventoryBean);
//    }
}
