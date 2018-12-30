package com.fmi.mpr.hw.http.router;

import com.fmi.mpr.hw.http.common.HeaderField;
import com.fmi.mpr.hw.http.common.MimeType;
import com.fmi.mpr.hw.http.common.StatusCode;
import com.fmi.mpr.hw.http.request.MethodType;
import com.fmi.mpr.hw.http.request.RequestBuilder;
import com.fmi.mpr.hw.http.response.Response;
import com.fmi.mpr.hw.http.storage.FileStorage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.Optional;

public class GetHandler implements Handler {

    private FileStorage fileStorage;

    public GetHandler() {
        fileStorage = new FileStorage();
    }

    @Override
    public boolean canHandle(RequestBuilder.Request request) {
        return request.getMethodType().equals(MethodType.GET);
    }

    @Override
    public void handle(RequestBuilder.Request request, Response response) {
        Optional<Path> fileOpt = fileStorage.load(request.getPath());
        StatusCode statusCode = StatusCode.NOT_FOUND;
        if (fileOpt.isPresent()) {
            try {
                FileInputStream input = new FileInputStream(fileOpt.get().toFile());
                response.setContent(input);
                response.setHeader(HeaderField.CONTENT_TYPE, MimeType.getMimeType(fileOpt.get().getFileName().toString()));
                response.setHeader(HeaderField.CONTENT_LENGTH, fileOpt.get().toFile().length() + "");
                statusCode = StatusCode.OK;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        response.setStatusCode(statusCode);
    }
}
