package br.com.fiap.autenticacao.autenticacao.controllers;

import br.com.fiap.autenticacao.autenticacao.entities.Usuario;
import br.com.fiap.autenticacao.autenticacao.services.UsuarioService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> findAllUsuarios(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        logger.info("Iniciando listagem de usuários - Página: {}, Tamanho: {}", page, size);
        var usuarios = usuarioService.findAllUsuarios(page, size);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Usuario>> findUsuarioById(
            @PathVariable("id") Long id
    ) {
        logger.info("Buscando usuário com ID: {}", id);
        var usuario = usuarioService.findUsuariosById(id);
        return usuario.isPresent() ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Void> saveUsuario(
            @RequestBody @Valid Usuario usuario
    ) {
        logger.info("Criando um novo usuário: {}", usuario.getLogin());
        usuarioService.saveUsuario(usuario);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUsuario(
            @PathVariable("id") Long id,
            @RequestBody @Valid Usuario usuario
    ) {
        logger.info("Atualizando usuário com ID: {}", id);
        usuarioService.updateUsuario(usuario, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuarios(
            @PathVariable("id") Long id
    ) {
        logger.info("Deletando usuário com ID: {}", id);
        usuarioService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
