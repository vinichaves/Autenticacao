package br.com.fiap.autenticacao.autenticacao.services;

import br.com.fiap.autenticacao.autenticacao.entities.Usuario;
import br.com.fiap.autenticacao.autenticacao.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Optional;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;

    public AuthService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Optional<String> authenticate(String login, String senha) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findBylogin(login);

        if (usuarioOpt.isPresent() && usuarioOpt.get().getSenha().equals(senha)) {
            // Login e senha corretos - gerar o token
            String token = generateToken();
            // Opcional: Salvar o token no banco ou cache
            return Optional.of(token);
        }

        return Optional.empty();
    }

    private String generateToken() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] randomBytes = new byte[32];
        secureRandom.nextBytes(randomBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
    }
}
