package br.com.app.app_control.application.controller.authenticated.pedido;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.app_control.application.controller.AbstractRoutesController;
import br.com.app.app_control.application.usecase.pedido.CreatePedidoUseCase;
import br.com.app.app_control.infrastructure.adapter.UseCaseHandler;
import br.com.app.app_control.infrastructure.record.ResponseRecord;
import br.com.project.core.dto.PedidoDTO;
import jakarta.validation.Valid;

@RestController
public class CreatePedidoController extends AbstractRoutesController<CreatePedidoUseCase, PedidoDTO> {

    private final CreatePedidoUseCase createPedidoUseCase;

    public CreatePedidoController(final CreatePedidoUseCase createPedidoUseCase,
            UseCaseHandler<CreatePedidoUseCase, PedidoDTO> useCaseHandler) {
        super(useCaseHandler);
        this.createPedidoUseCase = createPedidoUseCase;
    }

    @Override
    @PostMapping("/private/create/pedido")
    @Secured("CLIENTE")
    public ResponseEntity<ResponseRecord> handleRoute(@Valid @RequestBody PedidoDTO input) {
        return this.useCaseHandler.handle(createPedidoUseCase, input);
    }
}
