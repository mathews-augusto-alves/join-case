package br.com.project.core.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
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
public class CategoriaDTO {

    @NotNull(message = "Categoria não identificada.")
    private Long id;

    private String nome;

    private String descricao;

    private LocalDateTime criadoEm;

    private LocalDateTime atualizadoEm;
}
