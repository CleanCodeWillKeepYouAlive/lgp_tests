package com.leadgen.tests.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * В этот класс можно добавлять новые поля, если Лидген умеет еще что-то присылать в ответе!
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParsedResponse {

    @JsonProperty("Response_code")
    int responseCode;

    @JsonProperty("Result")
    String result;

    @JsonProperty("Message")
    String message;

    @JsonProperty("LeadId")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    String leadId;

    @JsonProperty("AutologinUrl")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    String autoLoginUrl;

    @JsonProperty("Payout")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    String payout;

}
