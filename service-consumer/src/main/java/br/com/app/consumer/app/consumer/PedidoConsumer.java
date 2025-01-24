package br.com.app.consumer.app.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import br.com.app.consumer.app.constants.GeneralConstants;
import br.com.app.consumer.app.service.pedido.IPedidoService;
import br.com.app.consumer.app.service.statuspedido.IStatusPedidoService;
import br.com.project.core.constants.Constants;
import br.com.project.core.dto.EnviarEmail;
import br.com.project.core.entities.Pedido;
import br.com.project.core.entities.StatusPedido;

@Service
public class PedidoConsumer {

    private static final Logger logger = LoggerFactory.getLogger(PedidoConsumer.class);

    private final IPedidoService pedidoService;
    private final IStatusPedidoService statusPedidoService;
    private final KafkaTemplate<String, EnviarEmail> kafkaTemplate;

    public PedidoConsumer(final IPedidoService pedidoService,
                          final IStatusPedidoService statusPedidoService,
                          final KafkaTemplate<String, EnviarEmail> kafkaTemplate) {
        this.pedidoService = pedidoService;
        this.statusPedidoService = statusPedidoService;
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Listener Kafka que consome mensagens do tópico "processar-pedido".
     * Atualiza o status do pedido para "APROVADO" e envia uma notificação por e-mail.
     *
     * @param pedido Mensagem contendo os dados do pedido.
     */
    @KafkaListener(topics = Constants.PROCESSAR_PEDIDO_TOPIC, groupId = GeneralConstants.GROUP_ID)
    public void processarPedido(Pedido pedido) {
        try {
            atualizarStatusParaAprovado(pedido);
            enviarNotificacaoPorEmail(pedido);
            logger.info("Pedido ID: {} processado e aprovado com sucesso.", pedido.getId());
        } catch (Exception e) {
            logger.error("Erro ao processar o pedido ID: {}", pedido.getId(), e);
        }
    }

    /**
     * Atualiza o status do pedido para "APROVADO".
     *
     * @param pedido Pedido a ser atualizado.
     */
    private void atualizarStatusParaAprovado(Pedido pedido) {
        StatusPedido statusAprovado = statusPedidoService.findByNome(GeneralConstants.STATUS_APROVADO)
                .orElseThrow(() -> new IllegalArgumentException("Status 'APROVADO' não encontrado."));
        pedido.setStatus(statusAprovado);
        pedidoService.createOrUpdate(pedido);
    }

    /**
     * Envia uma notificação por e-mail informando o status atualizado do pedido.
     *
     * @param pedido Pedido cujos dados serão utilizados para enviar o e-mail.
     */
    private void enviarNotificacaoPorEmail(Pedido pedido) {
        EnviarEmail emailDTO = EnviarEmail.builder()
                .usuario(pedido.getUsuario())
                .titulo(GeneralConstants.EMAIL_TITULO)
                .menssagem(GeneralConstants.EMAIL_MENSAGEM)
                .build();
        kafkaTemplate.send(Constants.EMAIL_TOPIC, emailDTO);
    }
}
