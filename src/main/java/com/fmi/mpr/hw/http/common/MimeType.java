package com.fmi.mpr.hw.http.common;

public class MimeType {

    public static String getMimeType(String filename) {
        if (filename.endsWith(".png")) {
            return "image/png";
        }

        if (filename.endsWith(".jpg")) {
            return "image/jpeg";
        }

        if (filename.endsWith(".txt")) {
            return "text/plain";
        }

        return "text/plain";
    }
}
