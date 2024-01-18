package mx.edu.utez.almacen.model.ticket;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.almacen.model.client.ClientBean;
import mx.edu.utez.almacen.model.product.ProductBean;
import mx.edu.utez.almacen.model.saleproduct.ProductSale;
import mx.edu.utez.almacen.model.user.UserBean;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Set;

@Entity
//Le ponemos nombre
@Table(name="ticket")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TicketBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(columnDefinition = "DATE",nullable = false)
    private LocalDate saleDate;

    @Column(nullable = false)
    private Integer folio;

    @Column(columnDefinition = "TIME")
    private LocalTime saleHour;

    @Column(nullable = false)
    private double totPay;

    @Column(nullable = false)
    private  Integer totProduct;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",nullable = false)
    private  UserBean userBean;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id",nullable = false)
    private ClientBean clientBean;


    @OneToMany(mappedBy = "ticketBean")
    private Set<ProductSale>  productSales;


}
