package mx.edu.utez.almacen.service.rol;

import jakarta.persistence.NamedStoredProcedureQueries;
import lombok.AllArgsConstructor;
import mx.edu.utez.almacen.config.ApiResponse;
import mx.edu.utez.almacen.model.rol.RolBean;
import mx.edu.utez.almacen.model.rol.RolRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
//Data Trasnfer Object
public class RolService {

    private final RolRepository repository;
    public ResponseEntity<ApiResponse> getAll() {
        return new ResponseEntity<>(new ApiResponse(repository.findAll(),
                HttpStatus.OK), HttpStatus.OK);
    }
    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> save(RolBean rolBean) {
        Optional<RolBean> rol = repository.findByRol(rolBean.getRol());
        if (rol.isPresent()) {
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, true,
                    "El registro ya existe"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ApiResponse(repository.saveAndFlush(rolBean),
                HttpStatus.OK, false,
                "El registro fue exitos"), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> delete(String  rolBean) {
        Optional<RolBean> rol = repository.findByRol(rolBean);
        if (rol.isPresent()) {
            return new ResponseEntity<>(new ApiResponse(repository.deleteByRol(rolBean),
                    HttpStatus.OK, false,
                    "Eliminado de forma exitosa"), HttpStatus.OK);
        }


        return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, true,
                "El registro no existe"), HttpStatus.BAD_REQUEST);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> update(Long id,RolBean rolBean) {
        Optional<RolBean> rol = repository.findById(id);
        if (rol.isPresent()) {
            rolBean.setId(id);
            return new ResponseEntity<>(new ApiResponse(repository.saveAndFlush(rolBean),
                    HttpStatus.OK, false,
                    "Actualizado de forma exitosa"), HttpStatus.OK);
        }

        return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, true,
                "El registro no existe"), HttpStatus.BAD_REQUEST);
    }



}
