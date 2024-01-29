package mx.edu.utez.almacen.controller.user;

import mx.edu.utez.almacen.config.ApiResponse;
import mx.edu.utez.almacen.controller.user.dto.UserDto;
import mx.edu.utez.almacen.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin({"*"})

public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("getAll")
    public ResponseEntity<ApiResponse> getAll(){
        return service.getAll();
    }
    @GetMapping("finfOne/{id}")

    @PostMapping("save")
    public  ResponseEntity<ApiResponse> save(@RequestBody UserDto dto){
        return service.save(dto.toEntity());
    }

    @PutMapping("update")
    public  ResponseEntity<ApiResponse> update(@RequestBody UserDto  dto){
        return  service.update(dto.toEntityId());
    }
    @DeleteMapping("delete/{id}")
    public  ResponseEntity<ApiResponse> delete(@PathVariable Long id){
        return  service.delete(id);
    }

}
