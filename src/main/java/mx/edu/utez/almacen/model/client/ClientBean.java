package mx.edu.utez.almacen.model.client;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.almacen.model.ticket.TicketBean;

import java.util.Set;

@Entity
@Table(name = "client")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class ClientBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String companName;

    @Column(length = 50, nullable = false)
    private String address;

    @Column(length = 10, nullable = false)
    private String phoneNumber;

    @Column(length = 150, nullable = false)
    private String email;

    @OneToMany(mappedBy = "clientBean")
    private Set<TicketBean> ticketBean;

    public ClientBean(String companName, String address, String phoneNumber, String email) {
        this.companName = companName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public ClientBean(Long id, String companName, String address, String phoneNumber, String email) {
        this.id = id;
        this.companName = companName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
}
