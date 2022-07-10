package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.service.interfaces.MailService;
import org.example.service.interfaces.MonitoringService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

@Slf4j
@Service
@RequiredArgsConstructor
public class MonitoringServiceImpl implements MonitoringService {

    private final MailService mailService;

    @Value("${monitoring.timeout}")
    private Integer timeout;
    @Value("${monitoring.url}")
    private String url;
    @Value("${monitoring.port}")
    private Integer port;

    private Boolean schedulerEnable = true;
    private Boolean lastStatus;

    @Override
    public void stop() {
        schedulerEnable = false;
        log.info("Site availability monitoring has been stopped");
    }

    @Override
    public void start() {
        schedulerEnable = true;
        log.info("Site availability monitoring has been started");
    }

    @Override
    public Boolean lastAvailability() {
        log.info("Result of lastAvailability() method: " + lastStatus);
        return lastStatus;
    }

    @Override
    @Scheduled(cron = "${monitoring.scheduler.availability}")
    public void cronPingAvailability() {
        if (schedulerEnable) {
            try (Socket socket = new Socket()) {
                socket.connect(new InetSocketAddress(url, port), timeout);
                lastStatus = true;
                log.info("Site available");
            } catch (IOException e) {
                lastStatus = false;
                log.info("Site unavailable");
            } finally {
                mailService.sendStatus(lastStatus);
            }
        }
    }
}
