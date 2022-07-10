package org.example.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.example.service.interfaces.MonitoringService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/monitoring")
@RequiredArgsConstructor
public class MonitoringController {

    private final MonitoringService monitoringService;

    @PatchMapping(path = "/start")
    @ApiOperation(value = "Start monitoring a resource")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully start monitoring")
    })
    public ResponseEntity<Void> startMonitoring() {
        monitoringService.start();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(path = "/stop")
    @ApiOperation(value = "Stop monitoring a resource")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully stop monitoring")
    })
    public ResponseEntity<Void> stopMonitoring() {
        monitoringService.stop();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/availability", produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get resource availability")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Resource availability received successfully")
    })
    public ResponseEntity<Boolean> checkResourceAvailability() {
        return ResponseEntity.ok(monitoringService.lastAvailability());
    }
}
