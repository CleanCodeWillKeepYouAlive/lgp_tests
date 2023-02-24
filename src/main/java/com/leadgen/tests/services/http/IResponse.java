package com.leadgen.tests.services.http;

import com.leadgen.tests.dto.RequestTemplate;

public interface IResponse {

    int getResponseCode();

    String getResponseBody();

    RequestTemplate getRequest();
}
