package mx.edu.utez.almacen.controller.category;

import lombok.AllArgsConstructor;
import mx.edu.utez.almacen.config.ApiResponse;
import mx.edu.utez.almacen.controller.category.dto.CategoryDto;
import mx.edu.utez.almacen.service.category.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
@CrossOrigin(origins = {"*"})
@AllArgsConstructor

public class CategoryController {

    private final CategoryService service;
    @GetMapping("getAll")
    public ResponseEntity<ApiResponse> getAll(){
        return  service.getAll();
    }

    @PostMapping("crate")
    public  ResponseEntity<ApiResponse> create(@RequestBody CategoryDto dto){
        return service.create(dto.toEntity());
    }

    @DeleteMapping("delete/{nombre}")
    public  ResponseEntity<ApiResponse> delete(@PathVariable String nombre){
        return  service.delete(nombre);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id,@RequestBody CategoryDto dto){
        return service.update(id,dto.toEntity());
    }
}
