package br.com.fiap.autenticacao.autenticacao.repositories;

import br.com.fiap.autenticacao.autenticacao.entities.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository {

    Optional<Usuario> findById(Long id);

    Optional<Usuario> findBylogin(String login);

    List<Usuario> findAll(int size, int offset);

    Integer save(Usuario usuario);

    Integer update(Usuario usuario, Long id);

    Integer delete (Long id);
}
