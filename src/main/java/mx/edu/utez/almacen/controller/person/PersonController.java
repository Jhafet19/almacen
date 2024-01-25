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
    @PutMapping("update/{curp}")
    public ResponseEntity<ApiResponse>update(@PathVariable String curp,@RequestBody PersonDto dto){
        return service.update(curp,dto.toEntity());
    }
    @DeleteMapping("/delete/{curp}")
    public  ResponseEntity<ApiResponse> delete(@PathVariable String curp){
        return service.delete(curp);
    }
}
