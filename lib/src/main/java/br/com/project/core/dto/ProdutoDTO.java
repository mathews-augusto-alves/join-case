package br.com.project.core.dto;

import java.math.BigDecimal;
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
public class ProdutoDTO {

    private Long id;

    private String nome;

    private String descricao;

    private BigDecimal preco;
    
    private CategoriaDTO categoria;

    private LocalDateTime criadoEm;

    private LocalDateTime atualizadoEm;
}
