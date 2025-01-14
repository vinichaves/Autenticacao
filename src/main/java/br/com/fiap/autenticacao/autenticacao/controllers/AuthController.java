package br.com.fiap.autenticacao.autenticacao.controllers;

import br.com.fiap.autenticacao.autenticacao.entities.Login;
import br.com.fiap.autenticacao.autenticacao.entities.Usuario;
import br.com.fiap.autenticacao.autenticacao.services.AuthService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);


    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(
            @RequestBody @Valid Login credentials
    ) {
        String login = credentials.getLogin();
        String senha = credentials.getSenha();

        return authService.authenticate(login, senha)
                .map(token -> ResponseEntity.ok(Map.of("token", token)))
                .orElse(ResponseEntity.status(401).body(Map.of("error", "Invalid credentials")));
    }

}