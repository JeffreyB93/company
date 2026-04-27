package com.example.company.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${ati.api.base-url:https://ati.su}")
    private String apiBaseUrl;

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(apiBaseUrl)
                .defaultHeader("User-Agent", "Spring-WebClient")
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(16 * 1024))
                .build();
    }
}
