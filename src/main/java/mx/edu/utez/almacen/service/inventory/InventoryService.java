package mx.edu.utez.almacen.service.inventory;

import lombok.AllArgsConstructor;
import mx.edu.utez.almacen.config.ApiResponse;
import mx.edu.utez.almacen.model.inventory.InventoryBean;
import mx.edu.utez.almacen.model.inventory.InventoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class InventoryService {

    private final InventoryRepository repository;
    public ResponseEntity<ApiResponse> getAll(){
        return new ResponseEntity<>(new ApiResponse( repository.findAll(), HttpStatus.OK,false,"Consulta exitos"),HttpStatus.OK);
    }

    @Transactional(rollbackFor={SQLException.class})
    public  ResponseEntity<ApiResponse>create(InventoryBean bean){
        return new ResponseEntity<>(new ApiResponse(repository.saveAndFlush(bean),HttpStatus.OK,
                false,"Creacion exitosa"),HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public  ResponseEntity<ApiResponse> delete(Long id){
        Optional<InventoryBean> foundInventory=repository.findById(id);
        if (foundInventory.isPresent()){
            repository.deleteById(id);
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK,false,"Eliminad exitosamente"),HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST,true,"Error al eliminar"),HttpStatus.BAD_REQUEST);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> update(InventoryBean  bean){
        Optional<InventoryBean> foundInventory=repository.findById(bean.getId());
    if(foundInventory.isPresent()){
        return new ResponseEntity<>(new ApiResponse(repository.saveAndFlush(bean),HttpStatus.OK,false,"Actualizacion exitosa"),HttpStatus.OK);
    }
        return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST,true,"Actualizacion fallida"),HttpStatus.BAD_REQUEST);

    }
}
