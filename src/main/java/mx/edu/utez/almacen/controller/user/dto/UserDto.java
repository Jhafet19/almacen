package mx.edu.utez.almacen.controller.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import mx.edu.utez.almacen.model.ticket.TicketBean;
import mx.edu.utez.almacen.model.user.UserBean;

import java.util.Set;

@Data
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String username;
    private String password;
    private Set<TicketBean> ticketBean;

    public UserBean toEntity(){
        return new UserBean(username,password,ticketBean);
    }
    public UserBean toEntityId(){
        return new UserBean(id,username,password,ticketBean);
    }
}
