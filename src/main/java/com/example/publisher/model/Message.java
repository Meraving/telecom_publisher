package com.example.publisher.model;

import lombok.Data;
import lombok.ToString;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Data
@ToString
@Component
public class Message {

    private static final Logger logger = LoggerFactory.getLogger(Message.class);

    private long msisdn;
    private Action action;
    private long timestamp;

    public Message() {
        this.msisdn = (long) (Math.random() * 100000000);
        this.action = (this.msisdn % 2 == 0 ? Action.PURCHASE : Action.SUBSCRIPTION);
        this.timestamp = Instant.now().getEpochSecond();
        logger.debug("Created message " + this);
    }

    public JSONObject getJson() {
        JSONObject messageJsonObject = new JSONObject();
        messageJsonObject.put("msisdn", this.getMsisdn());
        messageJsonObject.put("action", this.getAction());
        messageJsonObject.put("timestamp", this.getTimestamp());
        logger.debug("Created JSON " + messageJsonObject);
        return messageJsonObject;
    }
}
