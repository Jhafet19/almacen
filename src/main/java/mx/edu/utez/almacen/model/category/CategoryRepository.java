package mx.edu.utez.almacen.model.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryBean, Long> {

    Optional<CategoryBean> findByNombre(String nombre);

    Optional<CategoryBean> deleteByNombre(String  string);
}
