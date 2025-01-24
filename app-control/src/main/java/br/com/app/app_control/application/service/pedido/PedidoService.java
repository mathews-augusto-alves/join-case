package br.com.app.app_control.application.service.pedido;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.project.core.entities.Pedido;
import br.com.project.core.projection.PedidoProjection;
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

    @Override
    public Optional<Pedido> findById(Long id) {
        return pedidoRepository.findById(id);
    }

    @Override
    public List<PedidoProjection> findByUsuarioEmail(String email) {
        return pedidoRepository.findByUsuarioEmail(email);
    }
}
