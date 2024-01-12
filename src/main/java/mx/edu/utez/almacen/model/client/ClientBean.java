package mx.edu.utez.almacen.model.client;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

}
