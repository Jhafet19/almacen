package mx.edu.utez.almacen.controller.person;

import lombok.AllArgsConstructor;
import mx.edu.utez.almacen.config.ApiResponse;
import mx.edu.utez.almacen.controller.person.dto.PersonDto;
import mx.edu.utez.almacen.service.person.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/almacen/person")
@CrossOrigin(origins = {"*"})
@AllArgsConstructor

public class PersonController {
    private final PersonService service;

    @PostMapping("/")
    public ResponseEntity<ApiResponse> resgister(@RequestBody PersonDto  dto){
        return service.save(dto.toEntity());
    }
    @PutMapping("update/{id}")
    public ResponseEntity<ApiResponse>updateByCurp(@PathVariable Long id,@RequestBody PersonDto dto){
        return service.updateByCurp(id,dto.toEntity());
    }
    @DeleteMapping("/delete/{curp}")
    public  ResponseEntity<ApiResponse> delete(@PathVariable String curp){
        return service.delete(curp);
    }

    @GetMapping("/mostrar")
    public ResponseEntity<ApiResponse> getAll(){
        return service.getAll();
    }


}
