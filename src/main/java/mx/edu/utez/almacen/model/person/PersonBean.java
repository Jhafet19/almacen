package mx.edu.utez.almacen.model.person;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.almacen.model.user.UserBean;

import java.time.LocalDate;

@Entity
@Table(name = "person")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class PersonBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50,nullable = false)
    private String fullName;

    @Column(length = 50,nullable = false)
    private String surname;

    @Column(length = 50,nullable = false)
    private String lastname;

    @Column(columnDefinition = "DATE")
    private LocalDate birthday;

    @Column(length = 50,nullable = false)
    private String email;

    @Column(length = 10,nullable = false)
    private String phoneNumber;

    @OneToOne(mappedBy = "personBean",fetch = FetchType.EAGER)
    private UserBean userBean;

    @Column(length = 18,nullable = false)
    private String curp;

}
