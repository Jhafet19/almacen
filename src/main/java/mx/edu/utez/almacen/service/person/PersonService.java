package mx.edu.utez.almacen.service.person;

import lombok.AllArgsConstructor;
import mx.edu.utez.almacen.config.ApiResponse;
import mx.edu.utez.almacen.model.person.PersonBean;
import mx.edu.utez.almacen.model.person.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class PersonService {
    private final PersonRepository repository;

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> save(PersonBean personBean) {
        Optional<PersonBean> foundPerson = repository.findByCurp(personBean.getCurp());
        if (foundPerson.isPresent()) {
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, true,
                    "El registro ya existe"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ApiResponse(repository.saveAndFlush(personBean),
                HttpStatus.OK, false,
                "El registro fue exitos"), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse>delete(String  curp){
        Optional<PersonBean> foundPerson = repository.findByCurp(curp);
        if (foundPerson.isPresent()) {
            return new ResponseEntity<>(new ApiResponse(repository.deleteByCurp(curp),
                    HttpStatus.OK, false,
                    "Se elimino de forma Exitos"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, true,
                "El registro no existe"), HttpStatus.BAD_REQUEST);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse>update( String curp, PersonBean personBean){
        Optional<PersonBean> foundPerson = repository.findByCurp(curp);
        if (foundPerson.isPresent()) {
            return new ResponseEntity<>(new ApiResponse(repository.updateByCurp(personBean.getCurp()),
                    HttpStatus.OK, false,
                    "Se elimino de forma Exitos"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, true,
                "El registro no existe"), HttpStatus.BAD_REQUEST);
    }
}
