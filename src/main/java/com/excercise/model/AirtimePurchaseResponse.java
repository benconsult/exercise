package com.excercise.model;

import lombok.Data;

@Data
public class AirtimePurchaseResponse<T> {
    private String referenceId;
    private String requestId;
    private String responseCode;
    private String responseMessage;
    private T data;
}
