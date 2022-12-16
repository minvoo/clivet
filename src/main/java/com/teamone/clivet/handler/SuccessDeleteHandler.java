package com.teamone.clivet.handler;

import com.teamone.clivet.error.UserErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


public class SuccessDeleteHandler {

    private int httpStatus;
    private String message;
    private long timestamp;


    private SuccessDeleteHandler() {
        this.httpStatus = 200;
        this.message = "Delete successful";
        this.timestamp = System.currentTimeMillis();

    }

    public static ResponseEntity<?> handleDelete() {
        return new ResponseEntity<>(new SuccessDeleteHandler(), HttpStatus.OK);
    }
}
