package br.com.app.consumer.app.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import br.com.app.consumer.app.adpter.EmailSenderAdapter;
import br.com.app.consumer.app.constants.GeneralConstants;
import br.com.project.core.constants.Constants;
import br.com.project.core.dto.EnviarEmail;

@Service
public class EmailConsumer {

    private static final Logger logger = LoggerFactory.getLogger(EmailConsumer.class);

    private final EmailSenderAdapter emailSenderAdapter;

    public EmailConsumer(final EmailSenderAdapter emailSenderAdapter) {
        this.emailSenderAdapter = emailSenderAdapter;
    }

    /**
     * Listener Kafka que consome mensagens do tópico "processar-email".
     * Envia um e-mail com base na mensagem recebida.
     *
     * @param message Mensagem contendo os dados do e-mail.
     */
    @KafkaListener(topics = Constants.EMAIL_TOPIC, groupId = GeneralConstants.GRUPO_CONSUMIDOR)
    public void processarEmail(EnviarEmail message) {
        try {
            validarMensagem(message);
            emailSenderAdapter.sendEmail(message.getUsuario().getEmail(), message.getTitulo(), message.getMenssagem());
            logger.info("E-mail enviado para o usuário: {}", message.getUsuario().getEmail());
        } catch (Exception e) {
            logger.error("Erro ao processar e enviar e-mail para o usuário.", e);
        }
    }

    /**
     * Valida os dados da mensagem recebida.
     *
     * @param message Mensagem a ser validada.
     */
    private void validarMensagem(EnviarEmail message) {
        if (message == null) {
            throw new IllegalArgumentException("Mensagem de e-mail não pode ser nula.");
        }
        if (message.getUsuario() == null || message.getUsuario().getEmail() == null) {
            throw new IllegalArgumentException("Usuário ou e-mail do destinatário não pode ser nulo.");
        }
        if (message.getTitulo() == null || message.getTitulo().isBlank()) {
            throw new IllegalArgumentException("O título do e-mail não pode ser nulo ou vazio.");
        }
        if (message.getMenssagem() == null || message.getMenssagem().isBlank()) {
            throw new IllegalArgumentException("A mensagem do e-mail não pode ser nula ou vazia.");
        }
    }
}
