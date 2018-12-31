package com.fmi.mpr.hw.http.common;

public enum HeaderField {
    SERVER("Server"),
    CONTENT_LENGTH("Content-Length"),
    CONTENT_TYPE("Content-Type");

    private final String key;

    HeaderField(String key) {
        this.key = key.toLowerCase();
    }

    public String getKey() {
        return key;
    }
}
