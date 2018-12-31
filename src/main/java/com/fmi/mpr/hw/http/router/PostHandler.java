package com.fmi.mpr.hw.http.router;

import com.fmi.mpr.hw.http.common.HeaderField;
import com.fmi.mpr.hw.http.common.MimeType;
import com.fmi.mpr.hw.http.common.StatusCode;
import com.fmi.mpr.hw.http.request.MethodType;
import com.fmi.mpr.hw.http.request.RequestBuilder;
import com.fmi.mpr.hw.http.response.Response;
import com.fmi.mpr.hw.http.storage.FileStorage;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class PostHandler implements Handler {
    private FileStorage fileStorage;

    public PostHandler() {
        fileStorage = new FileStorage();
    }

    @Override
    public boolean canHandle(RequestBuilder.Request request) {
        return request.getMethodType().equals(MethodType.POST);
    }

    @Override
    public void handle(RequestBuilder.Request request, Response response) {
        System.out.println("POST received");
        try {
            fileStorage.save(request.getPath(), request.getContent(), Integer.parseInt(request.getHeader().get(HeaderField.CONTENT_LENGTH)));
            response.setStatusCode(StatusCode.OK);
            System.out.println("File saved");
        } catch (IOException e) {
            e.printStackTrace();
            response.setStatusCode(StatusCode.INTERNAL_SERVER_ERROR);
        }
        response.setContent(new ByteArrayInputStream("Uploaded successfully!".getBytes()));
        response.setHeader(HeaderField.CONTENT_TYPE, MimeType.TEXT_PLAIN);
    }
}
