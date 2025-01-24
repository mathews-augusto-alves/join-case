package br.com.app.app_control.application.controller.authenticated.pedido;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.app_control.application.controller.AbstractRoutesController;
import br.com.app.app_control.application.usecase.pedido.GetPedidoByUsuarioUseCase;
import br.com.app.app_control.infrastructure.adapter.UseCaseHandler;
import br.com.app.app_control.infrastructure.record.ResponseRecord;

@RestController
public class GetPedidoByUsuarioController extends AbstractRoutesController<GetPedidoByUsuarioUseCase, String> {

    private final GetPedidoByUsuarioUseCase getPedidoByUsuario;

    public GetPedidoByUsuarioController(final GetPedidoByUsuarioUseCase getPedidoByUsuario,
            UseCaseHandler<GetPedidoByUsuarioUseCase, String> useCaseHandler) {
        super(useCaseHandler);
        this.getPedidoByUsuario = getPedidoByUsuario;
    }

    @Override
    @GetMapping("/private/pedido-by-usuario/{email}")
    @Secured("CLIENTE")
    public ResponseEntity<ResponseRecord> handleRoute(@PathVariable String email) {
        return this.useCaseHandler.handle(getPedidoByUsuario, email);
    }
}
