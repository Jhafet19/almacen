package mx.edu.utez.almacen.model.rol;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.almacen.model.user.UserBean;

import java.util.Set;


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

    @OneToMany(mappedBy = "rolBean")
    private Set< UserBean> userBeans;

    public RolBean(String rol) {
        this.rol = rol;
    }
}
