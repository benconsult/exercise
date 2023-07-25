package com.excercise.controller;

import com.excercise.model.AirtimePurchaseRequest;
import com.excercise.model.Response;
import com.excercise.service.AirtimeVTUService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/airtime-purchase")
@RequiredArgsConstructor
public class AirtimePurchaseRestControllerController {

    private final AirtimeVTUService airtimeVTUService;

    @PostMapping
    public ResponseEntity<Response> airtimePurchase( @RequestBody AirtimePurchaseRequest requestBody) {
        Response response = airtimeVTUService.airtimePurchase(requestBody);
        return ResponseEntity.ok(response);
    }
}
