package br.com.app.app_control.application.controller.authenticated.pedido;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.app_control.application.controller.AbstractRoutesController;
import br.com.app.app_control.application.usecase.pedido.GetPedidoByIdUseCase;
import br.com.app.app_control.infrastructure.adapter.UseCaseHandler;
import br.com.app.app_control.infrastructure.record.ResponseRecord;

@RestController
public class GetPedidoByIdController extends AbstractRoutesController<GetPedidoByIdUseCase, Long> {

    private final GetPedidoByIdUseCase getPedidoById;

    public GetPedidoByIdController(final GetPedidoByIdUseCase getPedidoById,
            UseCaseHandler<GetPedidoByIdUseCase, Long> useCaseHandler) {
        super(useCaseHandler);
        this.getPedidoById = getPedidoById;
    }

    @Override
    @GetMapping("/private/pedido-by-id/{id}")
    @Secured("CLIENTE")
    public ResponseEntity<ResponseRecord> handleRoute(@PathVariable Long id) {
        return this.useCaseHandler.handle(getPedidoById, id);
    }
}
