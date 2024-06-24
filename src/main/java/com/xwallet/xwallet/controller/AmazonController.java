package com.xwallet.xwallet.controller;

import com.xwallet.xwallet.service.AmazonService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/amazon")
public class AmazonController {

    @Value("${spring.cloud.aws.sqs.endpoint}")
    private String endpoint;
    private final AmazonService amazonService;

    public AmazonController(AmazonService amazonService) {
        this.amazonService = amazonService;
    }

    @PostMapping("/sendQueue")
    public void sendQueue(@RequestBody String message) {
        amazonService.sendQueueMessage(String.valueOf(MessageBuilder.withPayload(message).build()));
    }

    @GetMapping("/sendNotification/{message}")
    public void sendNotification(@PathVariable String message) {
        amazonService.sendNotification(String.valueOf(MessageBuilder.withPayload(message).build()));
    }

}
