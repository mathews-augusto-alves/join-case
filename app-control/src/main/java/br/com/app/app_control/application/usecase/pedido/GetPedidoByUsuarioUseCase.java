package br.com.app.app_control.application.usecase.pedido;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.app.app_control.application.service.pedido.IPedidoService;
import br.com.app.app_control.infrastructure.contract.IExecution;
import br.com.app.app_control.infrastructure.record.ResponseRecord;
import br.com.project.core.projection.PedidoProjection;

@Service
public class GetPedidoByUsuarioUseCase implements IExecution<String> {

    private final IPedidoService pedidoService;

    public GetPedidoByUsuarioUseCase(final IPedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @Override
    public ResponseRecord execute(String request) {
        List<PedidoProjection> pedidos = pedidoService.findByUsuarioEmail(request);
        return new ResponseRecord(pedidos);
    }
}
