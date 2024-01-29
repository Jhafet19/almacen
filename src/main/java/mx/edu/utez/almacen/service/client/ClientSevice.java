package mx.edu.utez.almacen.service.client;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import mx.edu.utez.almacen.config.ApiResponse;
import mx.edu.utez.almacen.model.client.ClientBean;
import mx.edu.utez.almacen.model.client.ClientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class ClientSevice {
    private  final ClientRepository repository;

    public ResponseEntity<ApiResponse> getAll(){
        return new ResponseEntity<>(new ApiResponse(repository.findAll(), HttpStatus.OK
        ,false,"Consulta exitos"
        ),HttpStatus.OK);
    }
@Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> create(ClientBean clientBean){
        Optional<ClientBean> foundClient=repository.findByPhoneNumber(clientBean.getPhoneNumber());
    if (foundClient.isPresent()){
        return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST,true
        ,"El cliente ya existe"),HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(new ApiResponse(repository.saveAndFlush(clientBean),HttpStatus.OK,
            false,"Cracion exitos"),HttpStatus.OK);

    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> delete(Long id){
        Optional<ClientBean> foundClient=repository.findById(id);
        if (foundClient.isPresent()){
            repository.deleteById(id);
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK,
                    false,"Ciente eliminad exitosamente"),HttpStatus.OK);

        }
        return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST,true
                ,"El cliente no ya existe"),HttpStatus.BAD_REQUEST);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> update(ClientBean bean){
        Optional<ClientBean> foundClient=repository.findById(bean.getId());
        if (foundClient.isPresent()){
            return new ResponseEntity<>(new ApiResponse(repository.saveAndFlush(bean),HttpStatus.OK,
                    false,"Ciente eliminad exitosamente"),HttpStatus.OK);

        }
        return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST,true
                ,"El cliente no ya existe"),HttpStatus.BAD_REQUEST);
    }
}
