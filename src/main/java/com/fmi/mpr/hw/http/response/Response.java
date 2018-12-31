package com.fmi.mpr.hw.http.response;

import com.fmi.mpr.hw.http.common.Header;
import com.fmi.mpr.hw.http.common.HeaderField;
import com.fmi.mpr.hw.http.common.IOUtil;
import com.fmi.mpr.hw.http.common.StatusCode;

import java.io.*;

public class Response {
    private String httpVersion;
    private Header header;
    private StatusCode statusCode;
    private InputStream content;

    public Response() {
        header = new Header();
        setHeader(HeaderField.SERVER, "FMI Project HTTP server");
        statusCode = StatusCode.OK;
        httpVersion = "HTTP/1.1";
        content = new ByteArrayInputStream("".getBytes());
    }

    public static Response BAD_REQUEST() {
        Response response = new Response();
        response.statusCode = StatusCode.BAD_REQUEST;
        return response;
    }

    public void setStatusCode(StatusCode statusCode) {
        this.statusCode = statusCode;
    }

    public void setHeader(HeaderField key, String value) {
        header.set(key.getKey(), value);
    }

    public void setHttpVersion(String httpVersion) {
        this.httpVersion = httpVersion;
    }

    public InputStream getContent() {
        return content;
    }

    public void setContent(InputStream content) {
        this.content = content;
    }

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();
        text.append(httpVersion);
        text.append(" ");
        text.append(statusCode.getStatusCode());
        text.append(" ");
        text.append(statusCode.getPhrase());
        text.append("\r\n");
        text.append(header.toString());
        return text.toString();
    }

    public void commit(OutputStream outputStream) throws IOException {
        writeResponseMeta(outputStream);
        IOUtil.writeTo(getContent(), outputStream);
        outputStream.flush();
        content.close();
    }


    private void writeResponseMeta(OutputStream outputStream) {
        PrintWriter out = new PrintWriter(outputStream);
        String serializedResponse = toString();
        out.println(serializedResponse);
        out.flush();
    }
}
