package com.excercise.client;

import com.excercise.client.impl.XpressPaymentRequestClient;
import com.excercise.model.AirtimePurchaseRequest;
import com.excercise.model.AirtimePurchaseResponse;
import com.excercise.utils.Utility;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;

import static org.mockito.Mockito.when;

public class HttpRequestClientTest {

    private HttpRequestClient<AirtimePurchaseRequest, AirtimePurchaseResponse<?>> httpRequestClient;
    private HttpClient httpClient;
    private final URI url = URI.create("https://test-host:9099/fake-path");
    private final ObjectMapper objectMapper = Utility.getSingletonObjectMapperInstance();

    @BeforeEach
    void setUp() {
        httpClient = Mockito.mock(HttpClient.class);
        httpRequestClient = new XpressPaymentRequestClient(httpClient);
    }

    @Test
    void should_throw_runtime_exception_when_requests_times_out() throws IOException {


        when(httpClient.execute(Mockito.any())).thenThrow(IOException.class);

        Assertions.assertThrows(RuntimeException.class, () -> httpRequestClient.postRequest(url, new AirtimePurchaseRequest()));

        Mockito.verify(httpClient).execute(Mockito.any());
    }

    @Test
    void should_return_correct_response_from_airtime_purchase_request() throws IOException {

        HttpResponse httpResponse = mockResponse(true);

        when(httpClient.execute(Mockito.any())).thenReturn(httpResponse);

        AirtimePurchaseResponse<?> expected = objectMapper.readValue(httpResponse.getEntity().getContent(), AirtimePurchaseResponse.class);

        AirtimePurchaseResponse<?> got = httpRequestClient.postRequest(url, new AirtimePurchaseRequest());

        Assertions.assertEquals(expected, got);

    }


    private HttpResponse mockResponse(boolean success) throws UnsupportedEncodingException {


        ProtocolVersion protocolVersion = new ProtocolVersion("http", 1, 1);

        int statusCode = success ? 200 : 400;
        String phrase = success ? "OK" : "BAD_REQUEST";

        BasicStatusLine statusLine = new BasicStatusLine(protocolVersion, statusCode, phrase);

        BasicHttpResponse basicHttpResponse = new BasicHttpResponse(statusLine);

        String json = """
                {
                "referenceId": "123467",
                "requestId": "113345",
                "responseCode": "string",
                "responseMessage": "string",
                "data": null \s
                }""";

        basicHttpResponse.setEntity(new StringEntity(json));

        return basicHttpResponse;
    }
}
