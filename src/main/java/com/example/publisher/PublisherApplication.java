package com.example.publisher;

import com.example.publisher.model.Spammer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class PublisherApplication {

    private static final Logger logger = LoggerFactory.getLogger(PublisherApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(PublisherApplication.class, args);

        logger.debug("Sending messages");
        new Spammer().sendMessages();
    }


}
