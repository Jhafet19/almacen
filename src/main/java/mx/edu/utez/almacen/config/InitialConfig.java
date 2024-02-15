package mx.edu.utez.almacen.config;

import jakarta.persistence.Column;
import mx.edu.utez.almacen.model.person.PersonRepository;
import mx.edu.utez.almacen.model.rol.RolBean;
import mx.edu.utez.almacen.model.rol.RolRepository;
import mx.edu.utez.almacen.model.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class InitialConfig implements CommandLineRunner {

    private final RolRepository rolRepository;
    private final UserRepository userRepository;
    private final PersonRepository personRepository;
    // private  final PasswordEncoder passwordEncoder;

    public InitialConfig(RolRepository rolRepository, UserRepository userRepository, PersonRepository personRepository) {
        this.rolRepository = rolRepository;
        this.userRepository = userRepository;
        this.personRepository = personRepository;
    }

    @Override
    public void run(String... args) throws Exception {
    
    }
}
