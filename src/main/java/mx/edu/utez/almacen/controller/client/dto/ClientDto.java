package mx.edu.utez.almacen.controller.client.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.edu.utez.almacen.model.client.ClientBean;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientDto {

    private Long id;

    private String companName;

    private String address;

    private String phoneNumber;

    private String email;

    public ClientBean toEntity(){
        return  new ClientBean(companName,address,phoneNumber,email);
    }

    public ClientBean toEntityId(){
        return  new ClientBean(id,companName,address,phoneNumber,email);
    }
}
