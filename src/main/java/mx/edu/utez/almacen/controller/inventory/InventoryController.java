package mx.edu.utez.almacen.controller.inventory;

import mx.edu.utez.almacen.config.ApiResponse;
import mx.edu.utez.almacen.controller.inventory.dto.InventoryDto;
import mx.edu.utez.almacen.service.inventory.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@CrossOrigin({"*"})
public class InventoryController {
    private  final InventoryService service;

    @GetMapping("getAll")
    public ResponseEntity<ApiResponse> getAll(){
        return service.getAll();
    }

    @PostMapping("create")
    public ResponseEntity<ApiResponse> create(@RequestBody InventoryDto dto){
        return service.create(dto.toEntity());
    }

    @PutMapping("update")
    public ResponseEntity<ApiResponse> update(@RequestBody InventoryDto dto){
        return service.update(dto.toEntityId());
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id){
        return service.delete(id);
    }

    public InventoryController(InventoryService service) {
        this.service = service;
    }
}
