package mx.edu.utez.almacen.controller.person.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import mx.edu.utez.almacen.model.person.PersonBean;

import java.time.LocalDate;

@NoArgsConstructor
@Data
public class PersonDto {

    private Long id;

    private String fullName;

    private String surname;

    private String lastname;

    private LocalDate birthday;

    private String email;

    private String phoneNumber;

    private String curp;

    public PersonBean toEntity(){

        return  new PersonBean(fullName,surname,lastname,birthday,email,phoneNumber,curp);
    }
}
