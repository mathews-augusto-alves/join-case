package br.com.app.consumer.app.adpter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import br.com.app.consumer.app.enums.EmailProvider;
import br.com.app.consumer.app.utils.CreateEmailSender;

@Component
public class EmailSenderAdapter {

    private static final Logger logger = LoggerFactory.getLogger(EmailSenderAdapter.class);
    
    @Value("${spring.mail.username}")
    private String sender;

    @Value("${spring.mail.password}")
    private String password;

    public void sendEmail(String to, String subject, String messageBody) {
        EmailProvider emailProvider = EmailResolverAdapter.resolveProvider(sender);
        JavaMailSender mailSender = CreateEmailSender.handle(emailProvider.getHost(), emailProvider.getPort(), sender,
                password, emailProvider.isUseTls());

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(messageBody);

        mailSender.send(message);
        logger.info("Email de confirmação de registro enviado com sucesso: {}", message);
    }
}
