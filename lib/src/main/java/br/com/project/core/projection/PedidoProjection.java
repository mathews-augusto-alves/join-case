package br.com.project.core.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import br.com.project.core.entities.PedidoProduto;
import br.com.project.core.entities.StatusPedido;

public interface PedidoProjection {
    Long getId();

    BigDecimal getTotal();

    StatusPedido getStatus();

    LocalDateTime getCriadoEm();

    LocalDateTime getAtualizadoEm();

    List<PedidoProduto> getPedidoProduto();
}
