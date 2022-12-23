package com.teamone.clivet.delete;


import com.teamone.clivet.error.UserErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SuccessDeleteHandler {

    @ExceptionHandler
    public ResponseEntity<SuccessDeleteResponse> handleDelete(HttpStatus httpStatus, Exception ex) {
        return new ResponseEntity<>(new SuccessDeleteResponse(httpStatus.value(),
                ex.getMessage(),System.currentTimeMillis()), HttpStatus.OK);
    }


}
