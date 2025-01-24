package br.com.app.consumer.app.service.pedido;

import br.com.project.core.entities.Pedido;

public interface IPedidoService {
    Pedido createOrUpdate(Pedido pedido);
}
