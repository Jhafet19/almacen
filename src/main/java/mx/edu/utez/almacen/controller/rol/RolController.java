package mx.edu.utez.almacen.controller.rol;


import mx.edu.utez.almacen.config.ApiResponse;
import mx.edu.utez.almacen.controller.rol.dto.RolDto;
import mx.edu.utez.almacen.service.rol.RolService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rol")
@CrossOrigin(origins = {"*"})
public class RolController {

    private final RolService service;

    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAll(){
        return service.getAll();
    }

    @PostMapping("save/")
    public  ResponseEntity<ApiResponse>register(@RequestBody  RolDto dto){
        return  service.save(dto.toEntity());
    }
    @DeleteMapping("delete/{rol}")
    public  ResponseEntity<ApiResponse> delete(@PathVariable String rol){
        return service.delete(rol);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id,@RequestBody RolDto dto){
        return service.update(id,dto.toEntity());
    }
    public RolController(RolService service) {
        this.service = service;
    }
}
