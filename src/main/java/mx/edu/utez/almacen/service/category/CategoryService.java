package mx.edu.utez.almacen.service.category;

import lombok.AllArgsConstructor;
import mx.edu.utez.almacen.config.ApiResponse;
import mx.edu.utez.almacen.model.category.CategoryBean;
import mx.edu.utez.almacen.model.category.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository repository;

    public ResponseEntity<ApiResponse> getAll() {
        return new ResponseEntity<>(new ApiResponse(repository.findAll(),
                HttpStatus.OK), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> create(CategoryBean categoryBean) {
        Optional<CategoryBean> foundCategory = repository.findByNombre(categoryBean.getNombre());
        if (foundCategory.isPresent()) {
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, true,
                    "El registro ya existe"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ApiResponse(repository.saveAndFlush(categoryBean),
                HttpStatus.OK,false,"Crado con exito"),HttpStatus.OK);
    }


    @Transactional(rollbackFor = {SQLException.class})
    public  ResponseEntity<ApiResponse> delete(String nombre){
        Optional<CategoryBean> foundCategory = repository.findByNombre(nombre);
        if(foundCategory.isPresent()){
            return new ResponseEntity<>(new ApiResponse(repository.deleteByNombre(nombre),
                    HttpStatus.OK,false,"Categoria eliminada exitosamente"
                    ),HttpStatus.OK);
        }
        return  new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST
        ,true,"Registro no encontrado"
        ),HttpStatus.BAD_REQUEST);
    }


    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> update(Long id,CategoryBean categoryBean){
        Optional<CategoryBean> foundCategory=repository.findById(id);
        if (foundCategory.isPresent()){
            categoryBean.setId(id);
            return new ResponseEntity<>(new ApiResponse(repository.saveAndFlush(categoryBean),
                    HttpStatus.OK,false,"Categoria actualizada"),HttpStatus.OK);
        }
        return  new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST,true,
        "Categoria no encontrada"
        ),HttpStatus.BAD_REQUEST);

    }
}
