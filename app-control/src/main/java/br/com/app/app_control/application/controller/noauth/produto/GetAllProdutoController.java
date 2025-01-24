package br.com.app.app_control.application.controller.noauth.produto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.app_control.application.controller.AbstractRoutesController;
import br.com.app.app_control.application.usecase.produto.GetAllProdutoUseCase;
import br.com.app.app_control.infrastructure.adapter.UseCaseHandler;
import br.com.app.app_control.infrastructure.dto.PaginationRequestDTO;
import br.com.app.app_control.infrastructure.record.ResponseRecord;
import jakarta.validation.Valid;

@RestController
public class GetAllProdutoController
        extends AbstractRoutesController<GetAllProdutoUseCase, PaginationRequestDTO> {

    private final GetAllProdutoUseCase getAllProdutoUseCase;

    public GetAllProdutoController(final GetAllProdutoUseCase getAllProdutoUseCase,
            UseCaseHandler<GetAllProdutoUseCase, PaginationRequestDTO> useCaseHandler) {
        super(useCaseHandler);
        this.getAllProdutoUseCase = getAllProdutoUseCase;
    }

    @Override
    @PostMapping("/public/produtos/all")
    public ResponseEntity<ResponseRecord> handleRoute(@Valid @RequestBody PaginationRequestDTO input) {
        return this.useCaseHandler.handle(getAllProdutoUseCase, input);
    }
}
