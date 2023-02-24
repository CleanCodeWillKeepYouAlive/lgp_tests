package com.leadgen.tests.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.annotation.Nonnull;
import java.net.URI;
import java.util.Collections;
import java.util.Map;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RequestTemplate {

    int timeout;
    URI uri;
    ContentType contentType;
    Map<String, String> headers;
    Map<String, Object> parameters;

    public static RequestTemplate of(int timeout,
                                     @Nonnull URI uri,
                                     @Nonnull ContentType contentType,
                                     @Nonnull Map<String, String> headers,
                                     @Nonnull Map<String, Object> parameters) {
        return new RequestTemplate(timeout, uri, contentType, headers, parameters);
    }

    public static RequestTemplate ofBasic(int timeout, @Nonnull URI uri, @Nonnull ContentType contentType) {
        return new RequestTemplate(timeout, uri, contentType, Collections.emptyMap(), Collections.emptyMap());
    }

}
