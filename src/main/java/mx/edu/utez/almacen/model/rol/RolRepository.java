package mx.edu.utez.almacen.model.rol;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<RolBean,Long> {
    Optional<RolBean> findByRol(String rol);

    Optional <RolBean> deleteByRol(String rol);

    @Modifying
    @Query(value = "INSERT INTO user_rol(rol_id,user_id) values (:roleId,:userId)",nativeQuery = true)
    int saveUserRol(@Param("roleId") Long rolId,@Param("userId") Long rolUserId);

}
