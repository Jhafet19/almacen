package mx.edu.utez.almacen.controller.rol.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import mx.edu.utez.almacen.model.person.PersonBean;
import mx.edu.utez.almacen.model.rol.RolBean;

@NoArgsConstructor
@Data
public class RolDto {

    private  Long id;

    private String rol;

    public RolBean toEntity(){
        return new RolBean(rol);
    }
}
