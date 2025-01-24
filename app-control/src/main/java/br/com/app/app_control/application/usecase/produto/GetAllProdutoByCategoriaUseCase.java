package br.com.app.app_control.application.usecase.produto;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.com.app.app_control.application.service.produto.IProdutoService;
import br.com.app.app_control.domain.dto.ProdutoFilterDTO;
import br.com.app.app_control.infrastructure.config.ModelMapperConfig;
import br.com.app.app_control.infrastructure.contract.IExecution;
import br.com.app.app_control.infrastructure.record.ResponseRecord;
import br.com.project.core.dto.ProdutoDTO;
import br.com.project.core.entities.Produto;

@Service
public class GetAllProdutoByCategoriaUseCase implements IExecution<ProdutoFilterDTO> {
    
    private final IProdutoService produtoService;
    private final ModelMapperConfig.Converter converter;

    public GetAllProdutoByCategoriaUseCase(final IProdutoService produtoService, final ModelMapperConfig.Converter converter) {
        this.produtoService = produtoService;
        this.converter = converter;
    }

    @Override
    public ResponseRecord execute(ProdutoFilterDTO produtoFilterDTO) {
        Page<Produto> result = produtoService.findAllByCategoria(produtoFilterDTO.getCategoriaId(), produtoFilterDTO.toPageable());
        return new ResponseRecord(converter.convertToDTOPage(result, ProdutoDTO.class));
    }
}
