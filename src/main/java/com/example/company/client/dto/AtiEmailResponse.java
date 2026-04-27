package com.example.company.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AtiEmailResponse {
    @JsonProperty("Error")
    private Boolean error;

    @JsonProperty("IsLimitOver")
    private Boolean isLimitOver;

    @JsonProperty("Message")
    private String message;

    @JsonProperty("IsPayed")
    private Boolean isPayed;

    @JsonProperty("LimitIsOverMessage")
    private String limitIsOverMessage;
}
