package br.com.project.core.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    private Long id;

    @NotEmpty(message = "É necessário preencher o nome para concluir o cadastro.")
    private String nome;

    @NotEmpty(message = "É necessário preencher o email para concluir o cadastro.")
    @Email(message = "O e-mail deve ter um formato válido.")
    private String email;

    @NotEmpty(message = "É necessário preencher a senha para concluir o cadastro.")
    @Size(min = 8, max = 20, message = "A senha deve ter entre 8 e 20 caracteres.")
    private String senha;

    private LocalDateTime criadoEm;

    private LocalDateTime atualizadoEm;

}
