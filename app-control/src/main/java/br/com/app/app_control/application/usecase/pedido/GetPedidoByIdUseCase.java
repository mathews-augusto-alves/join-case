package br.com.app.app_control.application.usecase.pedido;

import org.springframework.stereotype.Service;

import br.com.app.app_control.application.service.pedido.IPedidoService;
import br.com.app.app_control.infrastructure.config.ModelMapperConfig;
import br.com.app.app_control.infrastructure.contract.IExecution;
import br.com.app.app_control.infrastructure.exception.BusinessException;
import br.com.app.app_control.infrastructure.record.ResponseRecord;
import br.com.project.core.dto.PedidoDTO;
import br.com.project.core.entities.Pedido;

@Service
public class GetPedidoByIdUseCase implements IExecution<Long> {

    private final IPedidoService pedidoService;
    private final ModelMapperConfig.Converter converter;

    public GetPedidoByIdUseCase(final IPedidoService pedidoService, final ModelMapperConfig.Converter converter) {
        this.pedidoService = pedidoService;
        this.converter = converter;
    }

    @Override
    public ResponseRecord execute(Long request) {
        Pedido pedido = pedidoService.findById(request).orElseThrow(() -> new BusinessException("Pedido não encontrado."));
        return new ResponseRecord(converter.convertEntityToDTO(pedido, PedidoDTO.class));
    }
}
