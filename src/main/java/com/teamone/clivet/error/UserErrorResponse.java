package com.teamone.clivet.error;

public class UserErrorResponse {

    private int status;
    private String message;
    private long timeStamp;

    public UserErrorResponse(int status, String message, long timeStamp) {
        this.status = status;
        this.message = message;
        this.timeStamp = timeStamp;
    }

    public UserErrorResponse() {
    }

    public int getStatus() {
        return status;
    }

    public UserErrorResponse setStatus(int status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public UserErrorResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public UserErrorResponse setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
        return this;
    }
}
