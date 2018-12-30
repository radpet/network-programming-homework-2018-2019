package com.fmi.mpr.hw.http.request;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum MethodType {
    GET("GET"), POST("POST");
    private static Map<String, MethodType> lookupTable = new HashMap<>();

    static {
        lookupTable.put(GET.getKey(), GET);
        lookupTable.put(POST.getKey(), POST);
    }

    private String key;

    MethodType(String key) {
        this.key = key;
    }

    public static Optional<MethodType> lookup(String key) {
        MethodType methodType = lookupTable.get(key);

        if (methodType != null) {
            return Optional.of(methodType);
        }
        return Optional.empty();
    }

    public String getKey() {
        return key;
    }
}
