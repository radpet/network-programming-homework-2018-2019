package com.fmi.mpr.hw.http.response;

import com.fmi.mpr.hw.http.common.IOUtil;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

public class ResponseWriter {

    public static void write(OutputStream outputStream, Response response) throws IOException {
        writeResponseMeta(outputStream, response);
        IOUtil.writeTo(response.getContent(), outputStream);
        outputStream.flush();
    }

    private static void writeResponseMeta(OutputStream outputStream, Response response) {
        PrintWriter out = new PrintWriter(outputStream);
        String serializedResponse = response.toString();
        out.println(serializedResponse);
        out.flush();
    }
}
