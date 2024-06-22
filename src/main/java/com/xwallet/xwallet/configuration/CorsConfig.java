package com.xwallet.xwallet.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    private final String mappings;
    private final String allowedOrigins;
    private final String allowedMethods;
    private final String allowedHeaders;
    private final String allowCredentials;

    public CorsConfig(@Value("${spring.web.cors.mappings}") String mappings,
                      @Value("${spring.web.cors.allowed-origins}") String allowedOrigins,
                      @Value("${spring.web.cors.allowed-methods}") String allowedMethods,
                      @Value("${spring.web.cors.allowed-headers}") String allowedHeaders,
                      @Value("${spring.web.cors.allow-credentials}") String allowCredentials) {
        this.mappings = mappings;
        this.allowedOrigins = allowedOrigins;
        this.allowedMethods = allowedMethods;
        this.allowedHeaders = allowedHeaders;
        this.allowCredentials = allowCredentials;
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping(mappings)
                        .allowedOriginPatterns(allowedOrigins)
                        .allowedMethods(allowedMethods)
                        .allowedHeaders(allowedHeaders)
                        .allowCredentials(Boolean.parseBoolean(allowCredentials));
            }
        };
    }
}