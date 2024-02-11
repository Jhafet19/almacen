package mx.edu.utez.almacen.controller.productsale;

import lombok.AllArgsConstructor;
import mx.edu.utez.almacen.config.ApiResponse;
import mx.edu.utez.almacen.controller.productsale.dto.ProductSaleDto;
import mx.edu.utez.almacen.service.product.ProductService;
import mx.edu.utez.almacen.service.saleproduct.ProductSaleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/productSale")
@CrossOrigin({"*"})
@AllArgsConstructor
public class ProductSaleController {

//        private final ProductSaleService service;
//
//
//        @GetMapping("getAll")
//        public ResponseEntity<ApiResponse> getAll(){
//            return service.getAll();
//        }
//
//        @PostMapping("save")
//        public  ResponseEntity<ApiResponse> save(@RequestBody ProductSaleDto dto){
//            return service.save(dto.toEntity());
//        }
//
//        @PutMapping("update")
//        public  ResponseEntity<ApiResponse> update(@RequestBody ProductSaleDto  dto){
//            return  service.update(dto.toEntityId());
//        }
//        @DeleteMapping("delete/{id}")
//        public  ResponseEntity<ApiResponse> delete(@PathVariable Long id){
//            return  service.delete(id);
//        }

}
