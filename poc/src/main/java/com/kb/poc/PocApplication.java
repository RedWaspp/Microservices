package com.kb.poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableDiscoveryClient


public class PocApplication {
    public static void main(String[] args) throws InterruptedException {

        SpringApplication.run(PocApplication.class, args);
    }

}


