package com.fmi.mpr.hw.http.request;

import com.fmi.mpr.hw.http.common.Header;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;

public class RequestBuilder {

    public static Optional<Request> build(InputStream inputStream) throws IOException {
        Request request = new Request();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line = reader.readLine();
        Optional<RequestLine> requestLineOpt = readRequestLine(line);
        if (!requestLineOpt.isPresent()) {
            return Optional.empty();
        }
        RequestLine requestLine = requestLineOpt.get();
        request.methodType = requestLine.methodType;
        request.path = requestLine.path;
        request.httpVersion = requestLine.httpVersion;

        while (!(line = reader.readLine()).isEmpty()) {
            HeaderEntry headerEntry = readHeader(line);
            request.header.set(headerEntry.key, headerEntry.value);
        }

        return Optional.of(request);
    }

    private static Optional<RequestLine> readRequestLine(String line) {
        String[] tokens = line.split(" ");
        if (tokens.length < 3) {
            return Optional.empty();
        }

        Optional<MethodType> methodTypeOpt = MethodType.lookup(tokens[0].trim());
        if (!methodTypeOpt.isPresent()) {
            return Optional.empty();
        }
        RequestLine requestLine = new RequestLine();
        requestLine.methodType = methodTypeOpt.get();
        requestLine.path = tokens[1].trim();
        requestLine.httpVersion = tokens[2].trim();
        return Optional.of(requestLine);
    }


    private static HeaderEntry readHeader(String line) {
        int separatorIndex = line.indexOf(":");
        String name = line.substring(0, separatorIndex).trim();
        String value = line.substring(separatorIndex + 1).trim();

        return new HeaderEntry(name, value);
    }

    static class RequestLine {
        MethodType methodType;
        String path;
        String httpVersion;
    }

    static class HeaderEntry {
        String key;
        String value;

        HeaderEntry(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    public static class Request {
        private String path;
        private MethodType methodType;
        private Header header;
        private String httpVersion;

        Request() {
            header = new Header();
        }

        public MethodType getMethodType() {
            return methodType;
        }

        public String getPath() {
            return path;
        }

        public Header getHeader() {
            return header;
        }

        public String getHttpVersion() {
            return httpVersion;
        }
    }

}
