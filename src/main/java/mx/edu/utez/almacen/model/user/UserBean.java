package mx.edu.utez.almacen.model.user;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.almacen.model.person.PersonBean;
import mx.edu.utez.almacen.model.rol.RolBean;
import mx.edu.utez.almacen.model.ticket.TicketBean;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    //Esto define que tabla contiene a quien
    @ManyToMany
    @JoinColumn(name = "rol_id")
    private Set<RolBean> roles;

    @Column(columnDefinition = "TEXT")
    private String avatar;

    @Column(columnDefinition = "TIMESTAMP", insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime crateAt;

    @Column(columnDefinition = "BOOL DEFAULT true")
    private Boolean status;

    @Column(columnDefinition = "BOOL DEFAULT false")
    private Boolean blocked;

    private String token;

    public UserBean(String username, String password, PersonBean personBean) {
        this.username = username;
        this.password = password;
        this.personBean = personBean;
        this.status=true;
        this.crateAt=LocalDateTime.now();
        this.blocked=true;
    }

    public UserBean(String username, String password, PersonBean personBean,Set<RolBean> rolBeans) {
        this.username = username;
        this.password = password;
        this.personBean = personBean;
        this.status=true;
        this.crateAt=LocalDateTime.now();
        this.blocked=true;
        this.roles=rolBeans;
    }

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


