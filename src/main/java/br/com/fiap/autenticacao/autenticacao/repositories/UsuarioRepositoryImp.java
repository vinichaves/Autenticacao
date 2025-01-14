package br.com.fiap.autenticacao.autenticacao.repositories;

import br.com.fiap.autenticacao.autenticacao.entities.Usuario;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioRepositoryImp implements UsuarioRepository {

    private final JdbcClient jdbcClient;

    public UsuarioRepositoryImp(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return Optional.ofNullable(
                this.jdbcClient
                        .sql("SELECT * FROM usuarios WHERE id = :id")
                        .param("id", id)
                        .query(resultSet -> {
                            if (resultSet.next()) {
                                var usuario = new Usuario();
                                usuario.setId(resultSet.getLong("id"));
                                usuario.setNome(resultSet.getString("nome"));
                                usuario.setEmail(resultSet.getString("email"));
                                usuario.setSenha(resultSet.getString("senha"));
                                usuario.setEndereco(resultSet.getString("endereco"));
                                usuario.setLogin(resultSet.getString("login"));
                                usuario.setDataUltimaAlteracao(resultSet.getDate("dataUltimaAlteracao").toLocalDate());
                                return usuario;
                            }
                            return null;
                        })
        );
    }


    @Override
    public Optional<Usuario> findBylogin(String login) {
        return this.jdbcClient
                .sql("SELECT * FROM usuarios WHERE login = :login")
                .param("login", login)
                .query(Usuario.class)
                .optional();
    }

    @Override
    public List<Usuario> findAll(int size, int offset) {
        return this.jdbcClient
                .sql("SELECT * FROM usuarios LIMIT :size OFFSET :offset")
                .param("size", size)
                .param("offset", offset)
                .query(Usuario.class)
                .list();
    }

    @Override
    public Integer save(Usuario usuario) {
        return this.jdbcClient
                .sql("INSERT INTO usuarios (nome, email, login, senha, dataUltimaAlteracao, endereco) VALUES (:nome, :email, :login, :senha, :dataUltimaAlteracao, :endereco)")
                .param("nome", usuario.getNome())
                .param("email", usuario.getEmail())
                .param("login", usuario.getLogin())
                .param("senha", usuario.getSenha())
                .param("dataUltimaAlteracao", LocalDate.now())
                .param("endereco", usuario.getEndereco())
                .update();
    }

    @Override
    public Integer update(Usuario usuario, Long id) {
        usuario.setDataUltimaAlteracao(LocalDate.now());
        return this.jdbcClient
                .sql("UPDATE usuarios SET nome = :nome, email = :email, senha = :senha, dataUltimaAlteracao = :dataUltimaAlteracao, endereco = :endereco, login = :login WHERE id = :id")
                .param("id", id)
                .param("nome", usuario.getNome())
                .param("email", usuario.getEmail())
                .param("senha", usuario.getSenha())
                .param("dataUltimaAlteracao", usuario.getDataUltimaAlteracao())
                .param("endereco", usuario.getEndereco())
                .param("login", usuario.getLogin())
                .update();
    }

    @Override
    public Integer delete(Long id) {
        return this.jdbcClient
                .sql("DELETE FROM usuarios WHERE id = :id")
                .param("id", id)
                .update();
    }
}
