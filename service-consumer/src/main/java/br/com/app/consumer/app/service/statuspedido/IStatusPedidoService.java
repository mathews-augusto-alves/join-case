package br.com.app.consumer.app.service.statuspedido;

import java.util.Optional;

import br.com.project.core.entities.StatusPedido;

public interface IStatusPedidoService {
    Optional<StatusPedido> findByNome(String nome);
}
