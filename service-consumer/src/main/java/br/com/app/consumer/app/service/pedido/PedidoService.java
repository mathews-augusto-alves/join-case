package br.com.app.consumer.app.service.pedido;

import org.springframework.stereotype.Service;

import br.com.project.core.entities.Pedido;
import br.com.project.core.repository.PedidoRepository;

@Service
public class PedidoService implements IPedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(final PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public Pedido createOrUpdate(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }
}
