package br.com.app.app_control.application.controller.noauth.login;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.app_control.application.controller.AbstractRoutesController;
import br.com.app.app_control.application.usecase.login.LoginUseCase;
import br.com.app.app_control.domain.dto.LoginDTO;
import br.com.app.app_control.infrastructure.adapter.UseCaseHandler;
import br.com.app.app_control.infrastructure.record.ResponseRecord;
import jakarta.validation.Valid;

@RestController
public class LoginController extends AbstractRoutesController<LoginUseCase, LoginDTO> {

    private final LoginUseCase loginUseCase;

    public LoginController(final LoginUseCase loginUseCase,
            UseCaseHandler<LoginUseCase, LoginDTO> useCaseHandler) {
        super(useCaseHandler);
        this.loginUseCase = loginUseCase;
    }

    @Override
    @PostMapping("/public/login")
    public ResponseEntity<ResponseRecord> handleRoute(@Valid @RequestBody LoginDTO input) {
        return this.useCaseHandler.handle(loginUseCase, input);
    }
}
