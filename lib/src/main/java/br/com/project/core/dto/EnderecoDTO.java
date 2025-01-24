package br.com.project.core.dto;

import java.time.LocalDateTime;

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
public class EnderecoDTO {

    private Long id;

    private UsuarioDTO usuario;

    private String logradouro;

    private String cidade;

    private String estado;

    private String cep;

    private LocalDateTime criadoEm;

    private LocalDateTime atualizadoEm;
}
