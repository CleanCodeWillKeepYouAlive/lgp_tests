package com.leadgen.tests.services.http;

import com.leadgen.tests.dto.RequestTemplate;
import com.leadgen.tests.dto.ResponseTemplate;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Nonnull;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Slf4j
@UtilityClass
public class HttpFactory {

    private final HttpClient client = HttpClient.newBuilder()
            .followRedirects(HttpClient.Redirect.NEVER)
            .build();

    public static ResponseTemplate doGet(@Nonnull RequestTemplate sendData) throws Exception {
        final HttpRequest request = HttpUtils.initGetRequest(sendData);
        final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return ResponseTemplate.of(sendData, response.body(), response.statusCode());
    }

    public static ResponseTemplate doPost(@Nonnull RequestTemplate sendData) throws Exception {
        final HttpRequest request = HttpUtils.initPostRequest(sendData);
        final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return ResponseTemplate.of(sendData, response.body(), response.statusCode());
    }

    public static ResponseTemplate doGetAsync(@Nonnull RequestTemplate sendData) throws Exception {
        final HttpRequest request = HttpUtils.initGetRequest(sendData);
        final HttpResponse<String> response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).get();
        return ResponseTemplate.of(sendData, response.body(), response.statusCode());
    }

    public static ResponseTemplate doPostAsync(@Nonnull RequestTemplate sendData) throws Exception {
        final HttpRequest request = HttpUtils.initPostRequest(sendData);
        final HttpResponse<String> response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).get();
        return ResponseTemplate.of(sendData, response.body(), response.statusCode());
    }

    public static HttpResponse<String> getWithAuthentication(@Nonnull RequestTemplate sendData, @Nonnull String login,
                                                             @Nonnull String password) throws Exception {
        final HttpRequest request = HttpUtils.initGetRequest(sendData);
        return HttpClient.newBuilder()
                .authenticator(new Auth(login, password)).build()
                .send(request, HttpResponse.BodyHandlers.ofString());
    }

    @RequiredArgsConstructor
    private static class Auth extends Authenticator {
        private final String login;
        private final String password;

        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(this.login, this.password.toCharArray());
        }
    }
}
