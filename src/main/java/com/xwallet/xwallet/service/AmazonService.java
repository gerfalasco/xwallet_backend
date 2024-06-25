package com.xwallet.xwallet.service;

import com.amazonaws.services.sqs.model.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xwallet.xwallet.model.dto.MessageDTO;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.xwallet.xwallet.utils.Constants.AMAZON_SNS_ARN;
import static com.xwallet.xwallet.utils.Constants.AMAZON_SQS_URL;

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
        queueMessagingTemplate.send(AMAZON_SQS_URL, message);
    }

    @Scheduled(timeUnit = TimeUnit.SECONDS, fixedDelay = 10)
    public void listen() {
        try {
            queueMessagingTemplate.receive("ColaCore", Message.class)
                    .ifPresent(msg -> {
                        try {
                            String payload = extractPayload(String.valueOf(msg.getPayload())
                                    .replace("\r\n","")
                                    .replace("\s","")
                                    .replace("\\","")
                                    .replace("\"{","{")
                                    .replace("}\"","}"));
                            JSONObject jsonObject = new JSONObject(payload);
                            MessageDTO message = getMessage((JSONObject) jsonObject.get("Message"));
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    });
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

    private String extractPayload(String messageString) throws Exception {
        Pattern pattern = Pattern.compile("payload=(.*?),headers=", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(messageString);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            throw new Exception("No se encontró el payload en el mensaje.");
        }
    }

    private MessageDTO getMessage(JSONObject jsonMessage) {
        String operationType = jsonMessage.get("operationType").toString();
        Map<String, String> data = new HashMap<>();

        JSONObject jsonData = (JSONObject) jsonMessage.get("data");
        Iterator<String> keys = jsonData.keys();
        while(keys.hasNext()){
            String key = keys.next();
            String value = jsonData.get(key).toString();
            data.put(key, value);
        }

        return MessageDTO.builder().operationType(operationType).data(data).build();
    }
}
