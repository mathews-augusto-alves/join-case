package br.com.app.consumer.app.utils;

import java.util.Properties;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class CreateEmailSender {

    public static JavaMailSender handle(String host, int port, String username, String password, boolean useTls) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(username);
        mailSender.setPassword(password);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", useTls);
        props.put("mail.smtp.ssl.enable", !useTls ? "true" : "false");
        props.put("mail.debug", "false");

        return mailSender;
    }
}
