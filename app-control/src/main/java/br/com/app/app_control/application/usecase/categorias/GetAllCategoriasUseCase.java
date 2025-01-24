package br.com.app.app_control.application.usecase.categorias;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.app.app_control.application.service.categoria.ICategoriaService;
import br.com.app.app_control.infrastructure.config.ModelMapperConfig;
import br.com.app.app_control.infrastructure.contract.IExecution;
import br.com.app.app_control.infrastructure.record.ResponseRecord;
import br.com.project.core.dto.CategoriaDTO;
import br.com.project.core.entities.Categoria;

@Service
public class GetAllCategoriasUseCase implements IExecution<String> {

    private final ICategoriaService categoriaService;
    private final ModelMapperConfig.Converter converter;

    public GetAllCategoriasUseCase(final ICategoriaService categoriaService, final ModelMapperConfig.Converter converter) {
        this.categoriaService = categoriaService;
        this.converter = converter;
    }

    @Override
    public ResponseRecord execute() {
        List<Categoria> result = categoriaService.findAll();
        return new ResponseRecord(converter.convertToDTOList(result, CategoriaDTO.class));
    }
}
