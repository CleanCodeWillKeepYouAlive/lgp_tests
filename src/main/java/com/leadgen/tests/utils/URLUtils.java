package com.leadgen.tests.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Slf4j
public class URLUtils {

    public static String encode(final String value) {
        if (!StringUtils.isEmpty(value)) {
            try {
                return URLEncoder.encode(value, StandardCharsets.UTF_8);
            } catch (Exception e) {
                log.warn("Can't urlencode {}, error = {}", value, e);
            }
        }

        return StringUtils.EMPTY;
    }

    public static String decode(final String value) {
        if (!StringUtils.isEmpty(value)) {
            try {
                return URLDecoder.decode(value, StandardCharsets.UTF_8);
            } catch (Exception e) {
                log.warn("Can't urlencode {}, error = {}", value, e);
            }
        }

        return StringUtils.EMPTY;
    }
}
