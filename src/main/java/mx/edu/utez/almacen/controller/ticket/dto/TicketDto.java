package mx.edu.utez.almacen.controller.ticket.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import mx.edu.utez.almacen.model.client.ClientBean;
import mx.edu.utez.almacen.model.ticket.TicketBean;
import mx.edu.utez.almacen.model.user.UserBean;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
public class TicketDto {

    private  Long id;

    private LocalDate saleDate;

    private Integer folio;

    private LocalTime saleHour;

    private double totPay;

    private  Integer totProduct;

    private UserBean userBean;

    private ClientBean clientBean;

    public TicketBean toEntity(){
        return new TicketBean(saleDate,folio,saleHour,totPay,totProduct,userBean,clientBean);
    }

    public TicketBean toEntityId(){
        return new TicketBean(id,saleDate,folio,saleHour,totPay,totProduct,userBean,clientBean);
    }


}
