package br.com.app.app_control.application.controller.noauth.register;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.app_control.application.controller.AbstractRoutesController;
import br.com.app.app_control.application.usecase.usuario.CadastroUsuarioUseCase;
import br.com.app.app_control.infrastructure.adapter.UseCaseHandler;
import br.com.app.app_control.infrastructure.record.ResponseRecord;
import br.com.project.core.dto.UsuarioDTO;
import jakarta.validation.Valid;

@RestController
public class CadastroUsuarioController extends AbstractRoutesController<CadastroUsuarioUseCase, UsuarioDTO> {

    private final CadastroUsuarioUseCase cadastroUsuarioUseCase;

    public CadastroUsuarioController(final CadastroUsuarioUseCase cadastroUsuarioUseCase,
            UseCaseHandler<CadastroUsuarioUseCase, UsuarioDTO> useCaseHandler) {
                super(useCaseHandler);
        this.cadastroUsuarioUseCase = cadastroUsuarioUseCase;
    }

    @Override
    @PostMapping("/public/register")
    public ResponseEntity<ResponseRecord> handleRoute(@Valid @RequestBody UsuarioDTO input) {
        return this.useCaseHandler.handle(cadastroUsuarioUseCase, input);
    }
}
