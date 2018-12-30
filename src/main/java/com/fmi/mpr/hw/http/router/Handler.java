package com.fmi.mpr.hw.http.router;

import com.fmi.mpr.hw.http.request.RequestBuilder;
import com.fmi.mpr.hw.http.response.Response;

public interface Handler {

    boolean canHandle(RequestBuilder.Request request);

    void handle(RequestBuilder.Request request, Response response);
}
