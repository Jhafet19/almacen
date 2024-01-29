package mx.edu.utez.almacen.controller.client;

import mx.edu.utez.almacen.config.ApiResponse;
import mx.edu.utez.almacen.controller.client.dto.ClientDto;
import mx.edu.utez.almacen.service.client.ClientSevice;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/client")
@CrossOrigin(origins = {"*"})
public class ClientController {
    private  final ClientSevice sevice;

    @GetMapping("getAll")
    public ResponseEntity<ApiResponse> getAll(){
        return sevice.getAll();
    }

    @PostMapping("create")
    public ResponseEntity<ApiResponse> create(@RequestBody ClientDto dto){
        return sevice.create(dto.toEntity());
    }


    @DeleteMapping("delete/{id}")
    public  ResponseEntity<ApiResponse> delete(@PathVariable Long id){
        return sevice.delete(id);
    }

    @PutMapping("update")
    public ResponseEntity<ApiResponse> update(@RequestBody ClientDto dto){
        return sevice.update(dto.toEntityId());
    }

    public ClientController(ClientSevice sevice) {
        this.sevice = sevice;
    }
}
