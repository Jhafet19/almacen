package mx.edu.utez.almacen.model.user;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.almacen.model.person.PersonBean;
import mx.edu.utez.almacen.model.rol.RolBean;
import mx.edu.utez.almacen.model.ticket.TicketBean;

import java.util.Set;


@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    private String username;
    @Column(length = 50, nullable = false)
    private String password;

    @OneToOne
    //Esto define que tabla contiene a quien
    @JoinColumn(name = "rol_id")
    private RolBean rolBean;

    @OneToOne
    @JoinColumn(name = "person_id")
    private PersonBean personBean;

    @OneToMany(mappedBy = "userBean")
    private Set<TicketBean> ticketBean;

    public UserBean(Long id, String username, String password, Set<TicketBean> ticketBean) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.ticketBean = ticketBean;
    }



    public UserBean(String username, String password, Set<TicketBean> ticketBean) {
        this.username = username;
        this.password = password;
        this.ticketBean = ticketBean;
    }
}


