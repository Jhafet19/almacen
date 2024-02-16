package mx.edu.utez.almacen.service.auth;


import mx.edu.utez.almacen.config.ApiResponse;
import mx.edu.utez.almacen.model.user.UserBean;
import mx.edu.utez.almacen.security.jwt.JwtProvider;
import mx.edu.utez.almacen.service.user.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AuthService {
    private final UserService userService;
    private final JwtProvider provider;
    private final AuthenticationManager authenticationManager;


    @Transactional
    public ResponseEntity<ApiResponse> signIn(String username, String password) {
        try {
            Optional<UserBean> foundUser = userService.findUserByUsername(username);
            if (foundUser.isEmpty()) {
                return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, true,
                        "UserOrPasswordNotFound"),HttpStatus.BAD_REQUEST);
            }
            UserBean userBean=foundUser.get();
            if(!userBean.getStatus()){
                return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, true,
                        "UserNotEnabled"), HttpStatus.BAD_REQUEST);
            }
            if(!userBean.getBlocked()){
                return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, true,
                        "UserBlocked"), HttpStatus.BAD_REQUEST);
            }
            Authentication auth=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));

            SecurityContextHolder.getContext().setAuthentication(auth);
            String token=provider.generateToken(auth);
            return new ResponseEntity<>(new ApiResponse(HttpStatus.OK, false,
                    "Generado"), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            String message="CredentialsMismatch";
            if(e instanceof DisabledException){
                message="User disabled";
            }
            if (e instanceof AccountExpiredException){
                message="expiro";
            }
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, true,
                    "UserBlocked"), HttpStatus.BAD_REQUEST);
        }
    }

    public AuthService(UserService userService, JwtProvider provider, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.provider = provider;
        this.authenticationManager = authenticationManager;
    }
}
