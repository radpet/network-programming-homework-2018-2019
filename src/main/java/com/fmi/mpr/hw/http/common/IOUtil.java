package com.fmi.mpr.hw.http.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IOUtil {

    public static void writeTo(InputStream in, OutputStream out) throws IOException {
        byte[] buf = new byte[1024];
        int bytes;

        while ((bytes = in.read(buf)) != -1) {
            out.write(buf, 0, bytes);
        }
    }

    public static void writeTo(InputStream in, OutputStream out, int limit) throws IOException {
        byte[] buf = new byte[1024];
        int bytes;
        int written = 0;
        while ((bytes = in.read(buf)) != -1) {
            written += bytes;
            if (written >= limit) {
                break;
            }
            out.write(buf, 0, bytes);
        }
    }
}
