package mx.edu.utez.almacen.model.rol;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.almacen.model.user.UserBean;


@Entity
@Table(name="rol")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RolBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(length = 100,nullable = false)
    private String rol;

    //
    @OneToOne(mappedBy = "rolBean",fetch = FetchType.EAGER)
    private UserBean userBean;
}
