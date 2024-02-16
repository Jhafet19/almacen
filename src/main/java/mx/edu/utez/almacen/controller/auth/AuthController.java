package mx.edu.utez.almacen.controller.auth;

import mx.edu.utez.almacen.config.ApiResponse;
import mx.edu.utez.almacen.controller.auth.dto.SingDto;
import mx.edu.utez.almacen.service.auth.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin({"*"})
public class AuthController {

    private final AuthService service;


    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse> singIn(@RequestBody SingDto singDto) {
        return service.signIn(singDto.getUsername(), singDto.getPassword());
    }
}
