package com.teamone.clivet.controller.auth;

import com.sun.net.httpserver.HttpsServer;
import com.teamone.clivet.error.UserErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserRestExceptionHandler {

   @ExceptionHandler
   ResponseEntity<UserErrorResponse> handleException(HttpStatus httpStatus, Exception ex) {
        return new ResponseEntity<>(new UserErrorResponse(httpStatus.value(),
                ex.getMessage(),System.currentTimeMillis()), HttpStatus.BAD_REQUEST);
    }

}
