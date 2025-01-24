package br.com.app.app_control.application.service.statuspedido;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.project.core.entities.StatusPedido;
import br.com.project.core.repository.StatusPedidoRepository;

@Service
public class StatusPedidoService implements IStatusPedidoService {

    private final StatusPedidoRepository repository;

    public StatusPedidoService(final StatusPedidoRepository repository) { 
        this.repository = repository;
    }

    @Override
    public Optional<StatusPedido> findByNome(String nome) {
        return repository.findByNome(nome);
    }

}
