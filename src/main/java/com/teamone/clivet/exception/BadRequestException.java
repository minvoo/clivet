package com.teamone.clivet.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException() {
        super("Invalid arguments provided");
    }
}
