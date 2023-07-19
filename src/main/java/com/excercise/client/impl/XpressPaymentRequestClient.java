package com.excercise.client.impl;

import com.excercise.client.HttpRequestClient;
import com.excercise.model.AirtimePurchaseRequest;
import com.excercise.model.AirtimePurchaseResponse;
import com.excercise.utils.Utility;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;

@Service
@RequiredArgsConstructor
public class XpressPaymentRequestClient implements HttpRequestClient<AirtimePurchaseRequest, AirtimePurchaseResponse<?>> {

    @Qualifier("airtimePurchaseHttpClient")
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper = Utility.getSingletonObjectMapperInstance();

    @Override
    public AirtimePurchaseResponse<?> postRequest(URI url, AirtimePurchaseRequest httpRequest) {
        try {
            HttpPost request = new HttpPost(url);

            String requestBody = objectMapper.writeValueAsString(httpRequest);
            request.setEntity(new StringEntity(requestBody));

            HttpResponse response = httpClient.execute(request);

            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Could not complete purchase");
            }

            return objectMapper.readValue(response.getEntity().getContent(), AirtimePurchaseResponse.class);

        } catch (IOException e) {
            throw new RuntimeException("Could not complete purchase");
        }
    }
}
