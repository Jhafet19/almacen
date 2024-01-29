package mx.edu.utez.almacen.model.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<ClientBean,Long> {

    Optional<ClientBean> findByPhoneNumber(String number);
}
