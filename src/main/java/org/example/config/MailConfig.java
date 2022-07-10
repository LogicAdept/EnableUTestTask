package org.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class MailConfig {

    @Value("${monitoring.email.smtp.username}")
    private String username;
    @Value("${monitoring.email.smtp.appPassword}")
    private String password;
    @Value("${monitoring.email.smtp.host}")
    private String host;
    @Value("${monitoring.email.ssl.port}")
    private Integer sslPort;

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setUsername(username);
        mailSender.setPassword(password);
        mailSender.setHost(host);
        mailSender.setPort(sslPort);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.enable", "true");

        return mailSender;
    }
}
