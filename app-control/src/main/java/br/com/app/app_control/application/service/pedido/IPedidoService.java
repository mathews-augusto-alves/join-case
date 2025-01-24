package br.com.app.app_control.application.service.pedido;

import java.util.List;
import java.util.Optional;

import br.com.project.core.entities.Pedido;
import br.com.project.core.projection.PedidoProjection;

public interface IPedidoService {
    Pedido createOrUpdate(Pedido pedido);
    Optional<Pedido> findById(Long id);
    List<PedidoProjection> findByUsuarioEmail(String id);
}
