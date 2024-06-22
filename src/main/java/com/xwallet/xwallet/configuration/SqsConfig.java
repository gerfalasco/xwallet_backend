package com.xwallet.xwallet.configuration;

import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsSessionCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

@Configuration
public class SqsConfig {

    @Value("${spring.cloud.aws.region.static}")
    private String region;
    @Value("${spring.cloud.aws.credentials.access-key}")
    private String awsAccessKey;
    @Value("${spring.cloud.aws.credentials.secret-key}")
    private String awsSecretKey;
    @Value("${aws.session-id}")
    private String awsSessionId;

    @Bean
    SqsAsyncClient sqsAsyncClient(){
        return SqsAsyncClient
                .builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider
                        .create(AwsSessionCredentials.create(awsAccessKey, awsSecretKey, awsSessionId)))
                .build();
    }

    @Bean
    public SqsTemplate sqsTemplate(SqsAsyncClient sqsAsyncClient){
        return SqsTemplate.builder().sqsAsyncClient(sqsAsyncClient).build();
    }

}

