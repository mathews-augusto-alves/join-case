package br.com.app.app_control.application.usecase.pedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import br.com.app.app_control.application.service.pedido.IPedidoService;
import br.com.app.app_control.application.service.statuspedido.IStatusPedidoService;
import br.com.app.app_control.application.service.usuario.IUsuarioService;
import br.com.app.app_control.infrastructure.adapter.SendMessageToTopicAdapter;
import br.com.app.app_control.infrastructure.config.ModelMapperConfig;
import br.com.app.app_control.infrastructure.contract.IExecution;
import br.com.app.app_control.infrastructure.exception.BusinessException;
import br.com.app.app_control.infrastructure.record.ResponseRecord;
import br.com.project.core.dto.PedidoDTO;
import br.com.project.core.entities.Pedido;
import br.com.project.core.entities.PedidoProduto;
import br.com.project.core.entities.StatusPedido;
import br.com.project.core.entities.Usuario;

@Service
public class CreatePedidoUseCase implements IExecution<PedidoDTO> {

    private final IPedidoService pedidoService;
    private final IStatusPedidoService statusPedidoService;
    private final IUsuarioService usuarioService;
    private final ModelMapperConfig.Converter converter;
    private final SendMessageToTopicAdapter<Pedido> sendMessageAdapter;

    public CreatePedidoUseCase(final IPedidoService pedidoService,
                               final ModelMapperConfig.Converter converter,
                               final IStatusPedidoService statusPedidoService,
                               final IUsuarioService usuarioService,
                               final SendMessageToTopicAdapter<Pedido> sendMessageAdapter) {
        this.pedidoService = pedidoService;
        this.converter = converter;
        this.statusPedidoService = statusPedidoService;
        this.usuarioService = usuarioService;
        this.sendMessageAdapter = sendMessageAdapter;
    }

    @Override
    public ResponseRecord execute(PedidoDTO request) {
        Pedido pedido = createPedidoFromRequest(request);
        Pedido pedidoSalvo = pedidoService.createOrUpdate(pedido);

        sendMessageAdapter.sendMessage(pedidoSalvo);

        return new ResponseRecord("Pedido concluído.",
                "Seu pedido está em processamento. Você receberá um email com a confirmação.");
    }

    private Pedido createPedidoFromRequest(PedidoDTO request) {
        StatusPedido statusPedido = getStatusPedido("EM_PROCESSAMENTO");
        Usuario usuario = getUsuarioByEmail(request.getUsuario().getEmail());

        Pedido pedido = converter.convertDTOToEntity(request, Pedido.class);
        populatePedido(pedido, statusPedido, usuario);

        return pedido;
    }

    private StatusPedido getStatusPedido(String statusNome) {
        return statusPedidoService.findByNome(statusNome)
                .orElseThrow(() -> new BusinessException("Houve um problema ao atualizar o status do pedido. Tente novamente."));
    }

    private Usuario getUsuarioByEmail(String email) {
        return usuarioService.findByEmail(email)
                .orElseThrow(() -> new BusinessException("Houve um problema ao recuperar as informações do usuário. Tente novamente."));
    }

    private void populatePedido(Pedido pedido, StatusPedido statusPedido, Usuario usuario) {
        LocalDateTime now = LocalDateTime.now();
        pedido.setAtualizadoEm(now);
        pedido.setCriadoEm(now);
        pedido.setStatus(statusPedido);
        pedido.setUsuario(usuario);

        pedido.getPedidoProduto().forEach(produto -> populatePedidoProdutoWithDefaults(produto, pedido, now));

        pedido.setTotal(calculateTotal(pedido));
    }

    private void populatePedidoProdutoWithDefaults(PedidoProduto produto, Pedido pedido, LocalDateTime timestamp) {
        produto.setAtualizadoEm(timestamp);
        produto.setCriadoEm(timestamp);
        produto.setPedido(pedido);
    }

    private BigDecimal calculateTotal(Pedido pedido) {
        return pedido.getPedidoProduto().stream()
                .map(produto -> produto.getProduto().getPreco().multiply(BigDecimal.valueOf(produto.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
