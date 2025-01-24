package br.com.app.app_control.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {

    @NotEmpty(message = "É necessário preencher o email para concluir o cadastro.")
    @Email(message = "O e-mail deve ter um formato válido.")
    private String email;

    @NotEmpty(message = "É necessário preencher a senha para concluir o cadastro.")
    @Size(min = 8, max = 20, message = "A senha deve ter entre 8 e 20 caracteres.")
    private String senha;
}
