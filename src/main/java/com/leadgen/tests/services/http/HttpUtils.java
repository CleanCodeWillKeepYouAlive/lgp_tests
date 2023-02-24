package com.leadgen.tests.services.http;

import com.leadgen.tests.dto.RequestTemplate;
import com.leadgen.tests.utils.CollectionUtils;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.net.http.HttpRequest;
import java.time.Duration;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.time.temporal.ChronoUnit.SECONDS;

@Slf4j
@UtilityClass
public class HttpUtils {

    @Nullable
    protected static HttpRequest initGetRequest(@Nonnull RequestTemplate data) {
        try {
            final HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                    .uri(data.getUri())
                    .setHeader("Content-type", data.getContentType().getValue())
                    .timeout(Duration.of(data.getTimeout(), SECONDS))
                    .GET();
            if (!data.getHeaders().isEmpty()) {
                requestBuilder.headers(toHeaders(data.getHeaders()));
            }

            return requestBuilder.build();

        } catch (Exception ex) {
            log.error("Error: {}; Cannot pars such URL: {};", ex.getMessage(), data.getUri().toString());
        }

        return null;
    }

    @Nullable
    protected static HttpRequest initPostRequest(@Nonnull RequestTemplate data) {
        try {
            final HttpRequest.Builder reqBuilder = HttpRequest.newBuilder()
                    .uri(data.getUri())
                    .setHeader("Content-type", data.getContentType().getValue())
                    .headers(toHeaders(data.getHeaders()))
                    .timeout(Duration.of(data.getTimeout(), SECONDS));

            if (!data.getHeaders().isEmpty()) {
                reqBuilder.headers(toHeaders(data.getHeaders()));
            }
            if (data.getParameters().isEmpty()) {
                throw new RuntimeException("Request body cannot be empty for POST request!");
            }

            reqBuilder.POST(HttpRequest.BodyPublishers.ofString(toJson(data.getParameters())));
            return reqBuilder.build();

        } catch (Exception ex) {
            log.error("Error: {}; Cannot pars such URL: {};", ex.getMessage(), data.getUri().toString());
        }

        return null;
    }

    private static String toJson(@Nonnull Map<String, Object> parameters) {
        final StringBuilder builder = new StringBuilder();
        builder.append("{\n");

        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            final String name = entry.getKey();
            final Object value = entry.getValue();

            if (StringUtils.isNotBlank(name) && Objects.nonNull(value)) {
                builder.append("\"").append(name).append("\":");

                if (value instanceof String) {
                    builder.append("\"").append(value).append("\",");

                } else {
                    builder.append(value).append(",");
                }
            }
        }

        builder.append("\n}");
        return builder.toString();
    }

    private static String[] toHeaders(@Nonnull Map<String, String> headers) {
        return CollectionUtils.streamOf(headers.entrySet())
                .flatMap(entry -> Stream.of(entry.getKey(), entry.getValue()))
                .collect(Collectors.joining(",")).split(",");
    }

}
