package mx.edu.utez.almacen.model.person;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonInventory extends JpaRepository<PersonBean,Long> {
}
