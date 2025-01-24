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
public class EstoqueDTO {
    private Long id;

    private ProdutoDTO produto;

    private Integer quantidade;

    private LocalDateTime criadoEm;

    private LocalDateTime atualizadoEm;

}
