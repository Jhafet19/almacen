package mx.edu.utez.almacen.model.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<PersonBean,Long> {
    Optional<PersonBean> findByCurp(String curp);

    Optional<PersonBean> deleteByCurp(String curp);

    PersonBean updateByCurp(String curp);
}
