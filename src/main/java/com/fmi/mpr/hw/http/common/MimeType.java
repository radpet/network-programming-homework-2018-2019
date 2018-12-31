package com.fmi.mpr.hw.http.common;

public class MimeType {

    public static final String PNG = "image/png";
    public static final String JPEG = "image/jpeg";
    public static final String TEXT_PLAIN = "text/PLAIN";

    public static String getMimeType(String filename) {
        if (filename.endsWith(".png")) {
            return PNG;
        }

        if (filename.endsWith(".jpg")) {
            return JPEG;
        }

        if (filename.endsWith(".txt")) {
            return TEXT_PLAIN;
        }

        return TEXT_PLAIN;
    }
}
