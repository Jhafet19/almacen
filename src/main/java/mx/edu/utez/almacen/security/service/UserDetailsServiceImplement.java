package mx.edu.utez.almacen.security.service;

import mx.edu.utez.almacen.model.user.UserBean;
import mx.edu.utez.almacen.model.user.UserRepository;
import mx.edu.utez.almacen.security.model.UserDetailsImpl;
import mx.edu.utez.almacen.service.user.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserDetailsServiceImplement  implements UserDetailsService {

    private  final UserService service;


    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserBean> foundUser=service.findUserByUsername(username);
        if (foundUser.isPresent()){
            return UserDetailsImpl.build(foundUser.get());

        }
        throw new UsernameNotFoundException("UserNotFound");

    }

    public UserDetailsServiceImplement(UserService service) {
        this.service = service;
    }
}
