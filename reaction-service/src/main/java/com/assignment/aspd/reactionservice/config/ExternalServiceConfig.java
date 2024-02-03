package com.assignment.aspd.reactionservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "backend")
@Data
//Store the configuration on which ip and port other services are running
public class ExternalServiceConfig {
    private ServiceConfig blogService;
    private ServiceConfig commentService;

    @Data
    public static class ServiceConfig {
        private String hostname;
        private String port;
    }
}
