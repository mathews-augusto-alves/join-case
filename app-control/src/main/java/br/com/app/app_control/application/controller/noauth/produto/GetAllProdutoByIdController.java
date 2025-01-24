package br.com.app.app_control.application.controller.noauth.produto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.app_control.application.controller.AbstractRoutesController;
import br.com.app.app_control.application.usecase.produto.GetAllProdutoByIdUseCase;
import br.com.app.app_control.domain.dto.ProdutoFilterDTO;
import br.com.app.app_control.infrastructure.adapter.UseCaseHandler;
import br.com.app.app_control.infrastructure.record.ResponseRecord;

@RestController
public class GetAllProdutoByIdController
        extends AbstractRoutesController<GetAllProdutoByIdUseCase, ProdutoFilterDTO> {

    private final GetAllProdutoByIdUseCase getAllProdutoByIdUseCase;

    public GetAllProdutoByIdController(final GetAllProdutoByIdUseCase getAllProdutoByIdUseCase,
            UseCaseHandler<GetAllProdutoByIdUseCase, ProdutoFilterDTO> useCaseHandler) {
        super(useCaseHandler);
        this.getAllProdutoByIdUseCase = getAllProdutoByIdUseCase;
    }

    @Override
    @PostMapping("/public/produtos-by-id")
    public ResponseEntity<ResponseRecord> handleRoute(@RequestBody ProdutoFilterDTO input) {
        return this.useCaseHandler.handle(getAllProdutoByIdUseCase, input);
    }
}
