package com.leadgen.tests.dto;

import com.leadgen.tests.services.http.IResponse;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ResponseTemplate implements IResponse {

    int responseCode;

    String responseBody;

    RequestTemplate request;

    public static ResponseTemplate of(RequestTemplate request, String body, int code) {
        return new ResponseTemplate(code, body, request);
    }
}
