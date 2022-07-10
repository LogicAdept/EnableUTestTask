package org.example.service.interfaces;

public interface MonitoringService {

    void start();

    void stop();

    Boolean lastAvailability();

    void cronPingAvailability();
}
