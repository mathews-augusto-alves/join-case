package br.com.app.app_control.application.controller.noauth.categorias;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.app_control.application.controller.AbstractRoutesController;
import br.com.app.app_control.application.usecase.categorias.GetAllCategoriasUseCase;
import br.com.app.app_control.infrastructure.adapter.UseCaseHandler;
import br.com.app.app_control.infrastructure.record.ResponseRecord;

@RestController
public class GetAllCategoriasController extends AbstractRoutesController<GetAllCategoriasUseCase, String> {

    private final GetAllCategoriasUseCase getAllCategoriasUseCase;

    public GetAllCategoriasController(final GetAllCategoriasUseCase getAllCategoriasUseCase,
            UseCaseHandler<GetAllCategoriasUseCase, String> useCaseHandler) {
        super(useCaseHandler);
        this.getAllCategoriasUseCase = getAllCategoriasUseCase;
    }

    @Override
    @PostMapping("/public/categorias/all")
    public ResponseEntity<ResponseRecord> handleRoute() {
        return this.useCaseHandler.handle(getAllCategoriasUseCase);
    }
}
