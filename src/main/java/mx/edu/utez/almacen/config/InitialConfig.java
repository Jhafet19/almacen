package mx.edu.utez.almacen.config;

import jakarta.persistence.Column;
import mx.edu.utez.almacen.model.person.PersonBean;
import mx.edu.utez.almacen.model.person.PersonRepository;
import mx.edu.utez.almacen.model.rol.RolBean;
import mx.edu.utez.almacen.model.rol.RolRepository;
import mx.edu.utez.almacen.model.user.UserBean;
import mx.edu.utez.almacen.model.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

@Configuration
public class InitialConfig implements CommandLineRunner {

    private final RolRepository rolRepository;
    private final UserRepository userRepository;
    private final PersonRepository personRepository;
     private  final PasswordEncoder passwordEncoder;

    public InitialConfig(RolRepository rolRepository, UserRepository userRepository, PersonRepository personRepository,PasswordEncoder passwordEncoder) {
        this.rolRepository = rolRepository;
        this.userRepository = userRepository;
        this.personRepository = personRepository;
        this.passwordEncoder=passwordEncoder;
    }

    @Transactional(rollbackFor = {SQLException.class})
    @Override
    public void run(String... args) throws Exception {
        RolBean adminRole = getOrSaveRole(new RolBean("ADMIN_ROLE"));
        getOrSaveRole(new RolBean("USER_ROLE"));
        getOrSaveRole(new RolBean("CLIENT_ROLE"));

        PersonBean person=getOrSavePersonBean(new PersonBean("Jhafet","Rodriguez","Garcia", LocalDate.of(2004,1,26),"asd"));

        UserBean userBean=getOrSaveUserBean(new UserBean("admin",passwordEncoder.encode("admin"),person));


    }

    @Transactional
    public RolBean getOrSaveRole(RolBean rolBean) {
        Optional<RolBean> foundRol = rolRepository.findByRol(rolBean.getRol());
        //Lo busca
        return foundRol.orElseGet(() -> rolRepository.saveAndFlush(rolBean));
    }

    @Transactional
    public PersonBean getOrSavePersonBean(PersonBean personBean) {
        Optional<PersonBean> foundPerson = personRepository.findByCurp(personBean.getCurp());
        //Lo busca
        return foundPerson.orElseGet(() -> personRepository.saveAndFlush(personBean));
    }

    @Transactional
    public UserBean getOrSaveUserBean(UserBean userBean) {
        Optional<UserBean> foundUsername = userRepository.findFirstByUsername(userBean.getUsername());
        //Lo busca
        return foundUsername.orElseGet(() -> userRepository.saveAndFlush(userBean));
    }

    @Transactional
    public void SaveUserRoles(Long id, Long roleId) {
        Long userRoleId = userRepository.getIdUserRoles(id, roleId);
        if (userRoleId == null) {
            userRepository.saveUserRol(id, roleId);
        }
    }
}
