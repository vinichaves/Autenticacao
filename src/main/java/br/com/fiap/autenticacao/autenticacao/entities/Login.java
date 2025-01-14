package br.com.fiap.autenticacao.autenticacao.entities;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Login {

    @NotBlank(message = "O campo 'login' é obrigatório.")
    private String login;

    @NotBlank(message = "O campo 'senha' é obrigatório.")
    private String senha;
}
