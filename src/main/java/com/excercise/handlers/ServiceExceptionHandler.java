package com.excercise.handlers;

import com.excercise.client.impl.XpressPaymentRequestClient;
import com.excercise.model.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ServiceExceptionHandler {

    private final Logger LOGGER = LoggerFactory.getLogger(XpressPaymentRequestClient.class);

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Response> handleException(RuntimeException ex) {

        LOGGER.error(ex.getMessage());

        Response response = new Response();
        response.setSuccessful(false);
        response.setMessage(ex.getMessage());

        return ResponseEntity.badRequest().body(response);
    }


}
