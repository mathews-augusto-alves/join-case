package br.com.project.core.dto;

import br.com.project.core.entities.Usuario;
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
public class EnviarEmail {
    private Usuario usuario;
    private String titulo;
    private String menssagem;
}
