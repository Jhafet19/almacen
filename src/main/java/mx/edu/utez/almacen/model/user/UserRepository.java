package mx.edu.utez.almacen.model.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserBean, Long> {
    Optional<UserBean> findFirstByUsername(String username);

    Optional<UserBean> findByPersonBeanCurp(String curp);

    Optional<UserBean> findByPersonBeanId(Long id);

    List<UserBean> findAllByStatus(Boolean status);

    @Query(value = "select * FROM  user u inner JOIN  person p ON u.person_id=p.id where  p.birthday between :fechaUna AND :fechaDos", nativeQuery = true)
    List<UserBean> getUSer(@Param("fechaUno") String starDate, @Param("fechaDos") String endDate);

    @Modifying
    @Query(value = "INSERT INTO user_rol(rol_id,user_id) values (:roleId,:userId)",nativeQuery = true)
    int saveUserRol(@Param("roleId") Long rolId,@Param("userId") Long rolUserId);

    @Query(value = "select user_id from user_roles where user_id = :userId and role_id = :roleId", nativeQuery = true)
    Long getIdUserRoles(Long userId, Long roleId);
}
