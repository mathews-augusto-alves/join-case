package br.com.app.consumer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.project.core.constants.Constants;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic processarPedidoTopic() {
        return new NewTopic(Constants.PROCESSAR_PEDIDO_TOPIC, 1, (short) 1);
    }

    @Bean
    public NewTopic emailTopic() {
        return new NewTopic(Constants.EMAIL_TOPIC, 1, (short) 1);
    }
}
