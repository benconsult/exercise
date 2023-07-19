package com.excercise.service.impl;

import com.excercise.client.HttpRequestClient;
import com.excercise.model.AirtimePurchaseRequest;
import com.excercise.model.AirtimePurchaseResponse;
import com.excercise.model.Response;
import com.excercise.service.AirtimeVTUService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class AirtimeVTUServiceImpl implements AirtimeVTUService {

    private final HttpRequestClient<AirtimePurchaseRequest, AirtimePurchaseResponse<?>> httpRequestClient;

    @Value("${airtime-purchase.url}")
    private final String url;

    @Override
    public Response airtimePurchase(AirtimePurchaseRequest airtimePurchaseRequest) {
        AirtimePurchaseResponse<?> httpResponse = httpRequestClient.postRequest(URI.create(url), airtimePurchaseRequest);
        return new Response(httpResponse.getResponseCode());
    }
}
