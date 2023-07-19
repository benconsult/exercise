package com.excercise.configurations;

import lombok.RequiredArgsConstructor;
import org.apache.http.Header;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class HttpClientConfig {

    @Value("${airtime-purchase.public-key}")
    private final String PUBLIC_KEY;

    @Bean("airtimePurchaseHttpClient")
    public HttpClient httpClient() {

        List<Header> defaultHeaders = List.of(
                new BasicHeader("Content-Type", "application/json"),
                new BasicHeader("Authorization", "Bearer " + PUBLIC_KEY)
        );

        return HttpClientBuilder.create()
                .setDefaultHeaders(defaultHeaders)
                .build();
    }
}
