package com.leadgen.tests.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum ContentType {

    APPLICATION_JSON("application/json"),
    APPLICATION_XML("application/xml"),
    URL_ENCODED("application/www-x-form-urlencoded")
    ;

    @Getter
    private final String value;

}
