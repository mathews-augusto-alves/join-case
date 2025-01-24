package br.com.app.app_control.application.controller.noauth.produto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.app_control.application.controller.AbstractRoutesController;
import br.com.app.app_control.application.usecase.produto.GetAllProdutoByCategoriaUseCase;
import br.com.app.app_control.domain.dto.ProdutoFilterDTO;
import br.com.app.app_control.infrastructure.adapter.UseCaseHandler;
import br.com.app.app_control.infrastructure.record.ResponseRecord;
import jakarta.validation.Valid;

@RestController
public class GetAllProdutoByCategoriaController
        extends AbstractRoutesController<GetAllProdutoByCategoriaUseCase, ProdutoFilterDTO> {

    private final GetAllProdutoByCategoriaUseCase getAllProdutoByCategoriaUseCase;

    public GetAllProdutoByCategoriaController(final GetAllProdutoByCategoriaUseCase getAllProdutoByCategoriaUseCase,
            UseCaseHandler<GetAllProdutoByCategoriaUseCase, ProdutoFilterDTO> useCaseHandler) {
        super(useCaseHandler);
        this.getAllProdutoByCategoriaUseCase = getAllProdutoByCategoriaUseCase;
    }

    @Override
    @PostMapping("/public/produtos-by-categorias/all")
    public ResponseEntity<ResponseRecord> handleRoute(@Valid @RequestBody ProdutoFilterDTO input) {
        return this.useCaseHandler.handle(getAllProdutoByCategoriaUseCase, input);
    }
}
