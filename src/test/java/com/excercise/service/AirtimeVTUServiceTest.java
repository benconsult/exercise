package com.excercise.service;

import com.excercise.client.HttpRequestClient;
import com.excercise.client.impl.XpressPaymentRequestClient;
import com.excercise.model.AirtimePurchaseRequest;
import com.excercise.model.AirtimePurchaseResponse;
import com.excercise.model.Response;
import com.excercise.service.impl.AirtimeVTUServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.net.URI;

public class AirtimeVTUServiceTest {

    private HttpRequestClient<AirtimePurchaseRequest, AirtimePurchaseResponse<?>> httpRequestClient;
    private AirtimeVTUService airtimeVTUService;

    @BeforeEach
    void setUp() {
        httpRequestClient = Mockito.mock(XpressPaymentRequestClient.class);
        airtimeVTUService = new AirtimeVTUServiceImpl(httpRequestClient, "");
    }

    @Test
    void airtime_purchase_should_fail_on_request_timeout_or_failed_http_response_code() {

        AirtimePurchaseRequest request = new AirtimePurchaseRequest();

        Mockito.when(httpRequestClient.postRequest(URI.create(""), request)).thenThrow(RuntimeException.class);

        Assertions.assertThrows(RuntimeException.class, () -> airtimeVTUService.airtimePurchase(request));

        Mockito.verify(httpRequestClient).postRequest(URI.create(""), request);

    }

    @Test
    void should_return_failed_status_if_airtime_request_does_not_return_success_response_code() {

        AirtimePurchaseRequest request = new AirtimePurchaseRequest();

        AirtimePurchaseResponse failedResponse = new AirtimePurchaseResponse();
        failedResponse.setResponseCode("400");

        Mockito.when(httpRequestClient.postRequest(URI.create(""), request)).thenReturn(failedResponse);

        Response response = airtimeVTUService.airtimePurchase(request);

        Assertions.assertFalse(response.isSuccessful());

        Mockito.verify(httpRequestClient).postRequest(URI.create(""), request);
    }

    @Test
    void should_return_success_status_if_airtime_request_returns_success_response_code() {
        AirtimePurchaseRequest request = new AirtimePurchaseRequest();

        AirtimePurchaseResponse successResponse = new AirtimePurchaseResponse();
        successResponse.setResponseCode("200");

        Mockito.when(httpRequestClient.postRequest(URI.create(""), request)).thenReturn(successResponse);

        Response response = airtimeVTUService.airtimePurchase(request);

        Assertions.assertTrue(response.isSuccessful());

        Mockito.verify(httpRequestClient).postRequest(URI.create(""), request);
    }
}
