package com.fmi.mpr.hw.http.router;

import com.fmi.mpr.hw.http.request.MethodType;
import com.fmi.mpr.hw.http.request.RequestBuilder;
import com.fmi.mpr.hw.http.response.Response;

public class PostHandler implements Handler {
    @Override
    public boolean canHandle(RequestBuilder.Request request) {
        return request.getMethodType().equals(MethodType.POST);
    }

    @Override
    public void handle(RequestBuilder.Request request, Response response) {

    }
}
