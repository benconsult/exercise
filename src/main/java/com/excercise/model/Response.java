package com.excercise.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
public class Response {

    private boolean successful;
    private String message;

    public Response(String responseCode) {
        this.setSuccessful(Objects.equals(responseCode, "200"));
    }
}
