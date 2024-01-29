package mx.edu.utez.almacen.model.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository  extends JpaRepository<ProductBean,Long> {
    Optional<ProductBean> getProductBeanById(Long id);


}
