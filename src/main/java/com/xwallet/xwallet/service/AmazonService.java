package com.xwallet.xwallet.service;

import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;

import java.util.concurrent.TimeUnit;

import static com.xwallet.xwallet.utils.Constants.AMAZON_SNS_ARN;

@Service
public class AmazonService {

    @Autowired
    private SqsTemplate queueMessagingTemplate;
    private final SnsClient snsClient;
    private final Logger logger = LoggerFactory.getLogger(AmazonService.class);

    public AmazonService(SnsClient snsClient) {
        this.snsClient = snsClient;
    }

    public void sendQueueMessage(String message) {
        queueMessagingTemplate.send("ColaCore", message);
    }

    @Scheduled(timeUnit = TimeUnit.SECONDS, fixedDelay = 10)
    public void listen() {
        try {
            queueMessagingTemplate.receive("ColaCore", String.class)
                    .ifPresent(msg -> logger.info(msg.getPayload()));
        } catch (Exception ex) {
            logger.info("No pudimos conectarnos a la queue.");
        }
    }

    public void sendNotification(String message) {
        PublishRequest publishRequest = PublishRequest.builder()
                .topicArn(AMAZON_SNS_ARN)
                .message(message)
                .build();
        PublishResponse publishResponse = snsClient.publish(publishRequest);
        logger.info("MessageId: {}", publishResponse.messageId());
    }
}
