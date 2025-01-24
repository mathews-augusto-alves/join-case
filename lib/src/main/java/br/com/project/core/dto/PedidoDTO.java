package br.com.project.core.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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
public class PedidoDTO {
    private Long id;

    private UsuarioDTO usuario;

    private BigDecimal total;

    private String status;

    private LocalDateTime criadoEm;

    private LocalDateTime atualizadoEm;

    private List<PedidoProdutoDTO> pedidoProduto;
}
