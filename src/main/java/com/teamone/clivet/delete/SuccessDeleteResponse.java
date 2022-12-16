package com.teamone.clivet.delete;

public class SuccessDeleteResponse {

    private int status;
    private String message;
    private long timeStamp;

    public SuccessDeleteResponse(int status, String message, long timeStamp) {
        this.status = status;
        this.message = message;
        this.timeStamp = timeStamp;
    }

    public SuccessDeleteResponse() {
    }

    public int getStatus() {
        return status;
    }

    public SuccessDeleteResponse setStatus(int status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public SuccessDeleteResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public SuccessDeleteResponse setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
        return this;
    }
}
