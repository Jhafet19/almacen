package mx.edu.utez.almacen.service.user;

import lombok.AllArgsConstructor;
import mx.edu.utez.almacen.config.ApiResponse;
import mx.edu.utez.almacen.model.person.PersonBean;
import mx.edu.utez.almacen.model.person.PersonRepository;
import mx.edu.utez.almacen.model.rol.RolBean;
import mx.edu.utez.almacen.model.rol.RolRepository;
import mx.edu.utez.almacen.model.user.UserBean;
import mx.edu.utez.almacen.model.user.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class UserService {
    private final UserRepository repository;
    private final RolRepository rolRepository;
    private final PersonRepository personRepository;
    public ResponseEntity<ApiResponse> getAll() {
        return new ResponseEntity<>(new ApiResponse(repository.findAll(),
                HttpStatus.OK), HttpStatus.OK);
    }
    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> save(UserBean userbean) {
        Optional<UserBean> foundUser = repository.findByUsername(userbean.getUsername());
        if (foundUser.isPresent()) {
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, true, "EL usuario ya existe"), HttpStatus.BAD_REQUEST);
        }
        if (userbean.getPersonBean() != null) {
            Optional<PersonBean> foundPerson = personRepository.findById(userbean.getPersonBean().getId());
            if (foundPerson.isPresent()){
                Optional<RolBean> foundRol =rolRepository.findById(userbean.getRolBean().getId());
                if (foundRol.isPresent()){
                    return new ResponseEntity<>(new ApiResponse(repository.saveAndFlush(userbean),HttpStatus.OK,false,"Usuario creado"),HttpStatus.OK);
                }else{
                    new ResponseEntity<>(new ApiResponse(rolRepository.saveAndFlush(userbean.getRolBean())
                            , HttpStatus.OK, true,
                            "El rol no existe"), HttpStatus.OK);
                }
            }else{
                new ResponseEntity<>(new ApiResponse(personRepository.saveAndFlush(userbean.getPersonBean())
                        , HttpStatus.OK, true,
                        "La persona no existe"), HttpStatus.OK);
            }
        }
        return  new ResponseEntity<>(new ApiResponse(repository.saveAndFlush(userbean)
                , HttpStatus.OK, true,
                "Ya la cree"), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> delete(Long id) {
        Optional<UserBean> foundSale = repository.findById(id);
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
    public ResponseEntity<ApiResponse> update(UserBean userbean) {
        Optional<UserBean> foundProduct = repository.findById(userbean.getId());
        if (foundProduct.isPresent()) {
            return new ResponseEntity<>(new ApiResponse(repository.saveAndFlush(userbean),
                    HttpStatus.OK, false,
                    "Actualizado de forma exitosa"), HttpStatus.OK);
        }

        return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, true,
                "El registro no existe"), HttpStatus.BAD_REQUEST);
    }

}
