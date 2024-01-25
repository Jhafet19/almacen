package mx.edu.utez.almacen.service.rol;

import jakarta.persistence.NamedStoredProcedureQueries;
import lombok.AllArgsConstructor;
import mx.edu.utez.almacen.config.ApiResponse;
import mx.edu.utez.almacen.model.rol.RolRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
