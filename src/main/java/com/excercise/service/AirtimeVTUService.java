package com.excercise.service;

import com.excercise.model.AirtimePurchaseRequest;
import com.excercise.model.Response;

public interface AirtimeVTUService {
    Response airtimePurchase(AirtimePurchaseRequest airtimePurchaseRequest);
}
