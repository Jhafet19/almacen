package mx.edu.utez.almacen.service.product;

import lombok.AllArgsConstructor;
import mx.edu.utez.almacen.config.ApiResponse;
import mx.edu.utez.almacen.model.category.CategoryBean;
import mx.edu.utez.almacen.model.category.CategoryRepository;
import mx.edu.utez.almacen.model.product.ProductBean;
import mx.edu.utez.almacen.model.product.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class ProductService {

    private  final ProductRepository repository;
    private final CategoryRepository categoryRepository;

    public ResponseEntity<ApiResponse> getAll(){
        return new ResponseEntity<>(new ApiResponse(repository.findAll(), HttpStatus.OK,false,"Consulta exitosa"),HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> save(ProductBean bean) {
        Optional<ProductBean> foundProduct = repository.findById(bean.getId());
        if (foundProduct.isPresent()) {
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, true, "El ya esta duplicado"), HttpStatus.BAD_REQUEST);

        }
        if (bean.getCategoryBean() != null) {
            Optional<CategoryBean> foundCategory = categoryRepository.findById(bean.getCategoryBean().getId());
            if (foundCategory.isPresent()) {
                return new ResponseEntity<>(new ApiResponse(repository.saveAndFlush(bean), HttpStatus.OK, false, "El producto fue creado"), HttpStatus.OK);

            } else {

                new ResponseEntity<>(new ApiResponse(categoryRepository.saveAndFlush(bean.getCategoryBean())
                        , HttpStatus.OK, true,
                        "La Categoia no existe"), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(new ApiResponse(repository.saveAndFlush(bean), HttpStatus.OK, false, "El producto fue creado"), HttpStatus.OK);

    }

    @Transactional(rollbackFor = {SQLException.class})
    public  ResponseEntity<ApiResponse> findOne(Long id){
        Optional<ProductBean> foundProduct=repository.findById(id);
        if (foundProduct.isPresent()){
            return  new ResponseEntity<>(new ApiResponse(repository.findById(id),HttpStatus.OK,false,"ConsultaExitosa exitosamente"),HttpStatus.OK);
        }
        return  new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST,true,"El producto no existe"),HttpStatus.BAD_REQUEST);

    }
    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> create(ProductBean productBean){
        return new ResponseEntity<>(new ApiResponse(repository.saveAndFlush(productBean),HttpStatus.OK,false,"Creacion exitosa"),HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public  ResponseEntity<ApiResponse> delete(Long id){
        Optional<ProductBean> foundProduct=repository.findById(id);
        if (foundProduct.isPresent()){
            repository.deleteById(id);
            return  new ResponseEntity<>(new ApiResponse(HttpStatus.OK,false,"Eliminado exitosamente"),HttpStatus.OK);
        }
        return  new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST,true,"Eliminacion fallisa"),HttpStatus.BAD_REQUEST);

    }
    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> update(ProductBean bean){
        Optional<ProductBean> foundProduct=repository.findById(bean.getId());
        if (foundProduct.isPresent()){
            return new  ResponseEntity<>(new ApiResponse(repository.saveAndFlush(bean),HttpStatus.OK,false,"Actualizacion realizada"),HttpStatus.OK);
        }
        return new  ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST,true,"El producto no existe"),HttpStatus.BAD_REQUEST);

    }

}
