package mx.edu.utez.almacen.service.saleproduct;

import lombok.AllArgsConstructor;
import mx.edu.utez.almacen.config.ApiResponse;
import mx.edu.utez.almacen.model.saleproduct.ProductSale;
import mx.edu.utez.almacen.model.saleproduct.ProductSaleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class ProductSaleService {

    private final ProductSaleRepository repository;

    public ResponseEntity<ApiResponse> getAll() {
        return new ResponseEntity<>(new ApiResponse(repository.findAll(),
                HttpStatus.OK), HttpStatus.OK);
    }
    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> save(ProductSale productSale) {

        return new ResponseEntity<>(new ApiResponse(repository.saveAndFlush(productSale),
                HttpStatus.OK, false,
                "El registro fue exitos"), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> delete(Long id) {
        Optional<ProductSale> foundSale = repository.findById(id);
        if (foundSale.isPresent()) {
            repository.deleteById(id);
            return new ResponseEntity<>(new ApiResponse(
                    HttpStatus.OK, false,
                    "Eliminado de forma exitosa"), HttpStatus.OK);
        }


        return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, true,
                "El registro no existe"), HttpStatus.BAD_REQUEST);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> update(ProductSale productSale) {
        Optional<ProductSale> foundProduct = repository.findById(productSale.getId());
        if (foundProduct.isPresent()) {
            return new ResponseEntity<>(new ApiResponse(repository.saveAndFlush(productSale),
                    HttpStatus.OK, false,
                    "Actualizado de forma exitosa"), HttpStatus.OK);
        }

        return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, true,
                "El registro no existe"), HttpStatus.BAD_REQUEST);
    }

}
