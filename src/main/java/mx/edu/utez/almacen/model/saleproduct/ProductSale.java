package mx.edu.utez.almacen.model.saleproduct;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.almacen.model.category.CategoryBean;
import mx.edu.utez.almacen.model.inventory.InventoryBean;
import mx.edu.utez.almacen.model.ticket.TicketBean;

@Entity
@Table(name="product_sale")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductSale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Double total;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ticket_id",nullable = false)
    private TicketBean ticketBean;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "inventory_id",nullable = false)
    private InventoryBean inventoryBean;

    public ProductSale(Long id, Integer quantity, Double total, TicketBean ticketBean, InventoryBean inventoryBean) {
        this.id = id;
        this.quantity = quantity;
        this.total = total;
        this.ticketBean = ticketBean;
        this.inventoryBean = inventoryBean;
    }

    public ProductSale(Integer quantity, Double total, TicketBean ticketBean, InventoryBean inventoryBean) {
        this.quantity = quantity;
        this.total = total;
        this.ticketBean = ticketBean;
        this.inventoryBean = inventoryBean;
    }
}
