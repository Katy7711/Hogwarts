package ru.hogwarts.school.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {
    Logger logger = LoggerFactory.getLogger(InfoController.class);
    private final int port;

    public InfoController(@Value("${server.port}") int port) {
        this.port = port;
    }

    @RequestMapping("/getPort")
    public int port() {
        logger.debug("Requesting port {}", port);
        return port;
    }
}
