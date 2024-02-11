package mx.edu.utez.almacen.controller.product;

import mx.edu.utez.almacen.config.ApiResponse;
import mx.edu.utez.almacen.controller.product.dto.ProductDto;
import mx.edu.utez.almacen.service.product.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
@CrossOrigin({"*"})

public class ProductController {
    private final ProductService service;

    @GetMapping("getAll")
    public ResponseEntity<ApiResponse> getAll(){
        return service.getAll();
    }
    @GetMapping("finfOne/{id}")
    public  ResponseEntity<ApiResponse> findOne(@PathVariable Long id){
        return  service.findOne(id);
    }

    @PostMapping("save")
    public  ResponseEntity<ApiResponse> save(@RequestBody ProductDto dto){
        return service.save(dto.toEntity());
    }

    @PutMapping("update")
    public  ResponseEntity<ApiResponse> update(@RequestBody ProductDto  dto){
        return  service.update(dto.toEntityId());
    }
    @DeleteMapping("delete/{id}")
    public  ResponseEntity<ApiResponse> delete(@PathVariable Long id){
        return  service.delete(id);
    }
    public ProductController(ProductService service) {
        this.service = service;
    }
}
