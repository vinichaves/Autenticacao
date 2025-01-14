package br.com.fiap.autenticacao.autenticacao.services;

import br.com.fiap.autenticacao.autenticacao.entities.Usuario;
import br.com.fiap.autenticacao.autenticacao.repositories.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private static final Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> findAllUsuarios(int page, int size) {
        Assert.isTrue(page > 0, "O número da página deve ser maior que zero");
        Assert.isTrue(size > 0, "O tamanho deve ser maior que zero");
        int offset = (page - 1) * size;
        return this.usuarioRepository.findAll(size, offset);
    }

    public Optional<Usuario> findUsuariosById(Long id) {
        return this.usuarioRepository.findById(id);
    }

    public void saveUsuario(Usuario usuario) {
        Assert.hasText(usuario.getNome(), "O nome não pode ser vazio");
        Assert.hasText(usuario.getEmail(), "O email não pode ser vazio");
        Assert.hasText(usuario.getLogin(), "O login não pode ser vazio");

        var save = this.usuarioRepository.save(usuario);
        Assert.state(save == 1, "Erro ao salvar usuário: " + usuario.getLogin());
    }

    public void updateUsuario(Usuario usuario, Long id) {
        var usuarioAtual = this.usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário com ID " + id + " não encontrado"));

        usuario.setNome(Optional.ofNullable(usuario.getNome()).orElse(usuarioAtual.getNome()));
        usuario.setEmail(Optional.ofNullable(usuario.getEmail()).orElse(usuarioAtual.getEmail()));
        usuario.setSenha(Optional.ofNullable(usuario.getSenha()).orElse(usuarioAtual.getSenha()));
        usuario.setEndereco(Optional.ofNullable(usuario.getEndereco()).orElse(usuarioAtual.getEndereco()));
        usuario.setDataUltimaAlteracao(LocalDate.now());

        var update = this.usuarioRepository.update(usuario, id);
        if (update == 0) {
            throw new RuntimeException("Erro ao atualizar usuário");
        }
    }

    public void deleteUsuario(Long id) {
        logger.info("Tentando deletar o usuário com ID {}", id);
        var delete = this.usuarioRepository.delete(id);
        if (delete == 0) {
            logger.error("Usuário com ID {} não encontrado", id);
            throw new RuntimeException("Usuário não encontrado");
        }
        logger.info("Usuário com ID {} deletado com sucesso", id);
    }

}