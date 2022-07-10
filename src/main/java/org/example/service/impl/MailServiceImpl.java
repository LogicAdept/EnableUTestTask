package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.service.interfaces.MailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;

    @Value("${monitoring.email.smtp.username}")
    private String from;
    @Value("${monitoring.email.message.destination}")
    private String destination;
    @Value("${monitoring.email.message.template}")
    private String template;
    @Value("${monitoring.email.message.subject}")
    private String subject;
    @Value("${monitoring.url}")
    private String url;
    @Value("${monitoring.port}")
    private Integer port;

    @Override
    public void sendStatus(Boolean availability) {
        var currentTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(subject);
        message.setFrom(from);
        message.setTo(destination);
        message.setText(String.format(template, url, availability, currentTime, port));

        mailSender.send(message);

        log.info("Mail with monitoring result has been sent");
    }
}
