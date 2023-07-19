package com.excercise.client;

import java.net.URI;

public interface HttpRequestClient<REQUEST, RESPONSE> {
    RESPONSE postRequest(URI url, REQUEST httpRequest);
}
