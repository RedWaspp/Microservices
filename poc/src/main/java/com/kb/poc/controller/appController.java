package com.kb.poc.controller;

import com.kb.poc.configuration.WebClientConfiguration;
import com.kb.poc.entity.myEntity;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/app")
public class appController {
    @Autowired
    WebClientConfiguration webClientConfiguration;

    @Autowired
    myEntity entity;

    @GetMapping(value = "/matchResult/{matchid}")
    @CircuitBreaker(name = "BACKEND", fallbackMethod = "otherResult")
    public myEntity footballResult(@PathVariable Integer matchid) {

        System.out.println("what is my match Id {} " + matchid);
        System.out.println("am footballResult being called");


        if (matchid != 1)
            throw new RuntimeException("We dont have a match result for the match yet");
        entity.setResponseString("Football club Paris won against club Barca. Result is 3-1");

        return entity;
    }

    public myEntity otherResult(Integer matchId, Throwable t) {
        System.out.println("am otherResult being called");
        entity.setResponseString(" Circuit breaking  : We dont have the result yet for  " + matchId + ", But we have a result for club P against club M and result is 4-1");
        return entity;
    }

    @GetMapping(value = "/defaultCall/{matchid}")
    public ResponseEntity defaultCall(@PathVariable Integer matchid) {
        ResponseEntity<myEntity> responseEntity = webClientConfiguration.getRestTemplate()
                .getForEntity("http://POC-SERVICE/app/matchResult/" + matchid,
                        myEntity.class);

        return responseEntity;
    }


}
