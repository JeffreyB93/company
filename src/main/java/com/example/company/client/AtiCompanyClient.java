package com.example.company.client;

import com.example.company.client.dto.AtiEmailResponse;
import com.example.company.exception.AtiApiException;
import com.example.company.exception.AtiResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Slf4j
@Component
@RequiredArgsConstructor
public class AtiCompanyClient {
    private final WebClient webClient;

    @Value("${app.cookies}")
    private String cookies;

    public AtiEmailResponse fetchEmail(String companyId, String contactId) {
        try {
            String uri = "/api/email/getEmail" +  String.format("/%s/%s", companyId, contactId);
            log.info("Calling ATI API: {}", uri);
            return webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path(uri)
                            .build())
                    .header("Cookie", cookies)
                    .retrieve()
                    .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
                            clientResponse -> {
                                log.error("ATI API error: {}", clientResponse.statusCode());
                                return clientResponse.bodyToMono(String.class)
                                        .flatMap(errorBody -> {
                                            log.error("Error body: {}", errorBody);
                                            return Mono.error(
                                                    new RuntimeException("API error: " + errorBody)
                                            );
                                        });
                            })
                    .bodyToMono(AtiEmailResponse.class)
                    .block(Duration.ofSeconds(10));
        } catch (WebClientResponseException.NotFound e) {
            log.warn("Email not found for companyId: {}, contactId: {}", companyId, contactId);
            throw new AtiResourceNotFoundException("Email not found for company: " + companyId + ", contact: " + contactId, e);
        } catch (Exception e) {
            log.error("Error fetching email: {}", e.getMessage());
            throw new AtiApiException("Failed to fetch email", e);
        }
    }
}
