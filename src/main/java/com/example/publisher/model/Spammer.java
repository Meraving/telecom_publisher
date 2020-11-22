package com.example.publisher.model;

import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class Spammer {


    private static final String url = "http://localhost:8080/rest/message";

    private static final long timer = 15;

    private static final Logger logger = LoggerFactory.getLogger(Spammer.class);


    public static void spam() {
        logger.debug("sending new message");
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Message message = new Message();
        HttpEntity<JSONObject> request =
                new HttpEntity<>(message.getJson(), headers);

        logger.info("Sending message " + request);

        ResponseEntity<String> responseEntityStr = restTemplate.
                postForEntity(url, request, String.class);

        logger.info("Message sent. Response: " + responseEntityStr.getStatusCode());

    }

    public void sendMessages() {
        logger.info("Sending messages. Timer: " + timer);
        logger.info("url: " + url);

        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(Spammer::spam, 0, timer, TimeUnit.SECONDS);
    }

}
