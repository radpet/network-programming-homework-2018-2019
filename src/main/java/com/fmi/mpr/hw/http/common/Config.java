package com.fmi.mpr.hw.http.common;

public class Config {
    public static final String SERVER_HOST = "fmi.server.host";
    public static final String SERVER_PORT = "fmi.server.port";
    public static final String FILE_BASE_PATH = "fmi.file.storage";

    public static String get(String key) {
        return System.getenv(key);
    }
}
