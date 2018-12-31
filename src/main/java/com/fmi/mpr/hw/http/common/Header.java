package com.fmi.mpr.hw.http.common;

import java.util.HashMap;
import java.util.Map;

public class Header {

    private Map<String, String> values;

    public Header() {
        values = new HashMap<>();
    }

    public void set(String key, String value) {
        values.put(key, value);
    }

    public String get(HeaderField field) {
        return values.get(field.getKey());
    }
    @Override
    public String toString() {
        StringBuilder serialized = new StringBuilder();

        values.entrySet().forEach(header -> {
            serialized.append(header.getKey());
            serialized.append(": ");
            serialized.append(header.getValue());
            serialized.append("\r\n");
        });

        return serialized.toString();
    }
}
