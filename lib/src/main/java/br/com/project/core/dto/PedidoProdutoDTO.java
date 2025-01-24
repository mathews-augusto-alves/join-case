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
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PedidoProdutoDTO {

    private Long id;

    private PedidoDTO pedido;

    private ProdutoDTO produto;

    private Integer quantidade;

    private BigDecimal preco;

    private LocalDateTime criadoEm;

    private LocalDateTime atualizadoEm;
}
