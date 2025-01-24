package br.com.app.app_control.infrastructure.adapter;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import br.com.project.core.constants.Constants;

@Component
public class SendMessageToTopicAdapter<T> {

    private final KafkaTemplate<String, T> kafkaTemplate;

    public SendMessageToTopicAdapter(final KafkaTemplate<String, T> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(T message) {
        kafkaTemplate.send(Constants.PROCESSAR_PEDIDO_TOPIC, message);
    }
}
