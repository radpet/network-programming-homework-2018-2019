package com.fmi.mpr.hw.http.common;

public enum StatusCode {
    NOT_FOUND(404, "Resource not found"),
    BAD_REQUEST(400, "Bad request"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    OK(200, "OK");

    private int statusCode;
    private String phrase;

    StatusCode(int statusCode, String phrase) {
        this.statusCode = statusCode;
        this.phrase = phrase;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getPhrase() {
        return phrase;
    }
}
