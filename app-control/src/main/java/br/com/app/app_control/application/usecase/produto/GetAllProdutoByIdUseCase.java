package br.com.app.app_control.application.usecase.produto;

import org.springframework.stereotype.Service;

import br.com.app.app_control.application.service.produto.IProdutoService;
import br.com.app.app_control.domain.dto.ProdutoFilterDTO;
import br.com.app.app_control.infrastructure.config.ModelMapperConfig;
import br.com.app.app_control.infrastructure.contract.IExecution;
import br.com.app.app_control.infrastructure.exception.BusinessException;
import br.com.app.app_control.infrastructure.record.ResponseRecord;
import br.com.project.core.dto.ProdutoDTO;
import br.com.project.core.entities.Produto;

@Service
public class GetAllProdutoByIdUseCase implements IExecution<ProdutoFilterDTO> {

    private final IProdutoService produtoService;
    private final ModelMapperConfig.Converter converter;

    public GetAllProdutoByIdUseCase(final IProdutoService produtoService, final ModelMapperConfig.Converter converter) {
        this.produtoService = produtoService;
        this.converter = converter;
    }

    @Override
    public ResponseRecord execute(ProdutoFilterDTO produtoFilterDTO) {
        if(produtoFilterDTO.getId() == null) {
            throw new BusinessException("Não foi possível recuperar as informações do produto. Tente novamente.");
        }
        Produto result = produtoService.findById(produtoFilterDTO.getId())
                .orElseThrow(() -> new BusinessException("Produto não encontrado."));
        return new ResponseRecord(converter.convertEntityToDTO(result, ProdutoDTO.class));
    }
}
