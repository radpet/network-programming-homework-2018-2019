package com.fmi.mpr.hw.http.router;

import com.fmi.mpr.hw.http.request.RequestBuilder;
import com.fmi.mpr.hw.http.response.Response;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Router {

    private static final List<Handler> handlers = Arrays.asList(new GetHandler(), new PostHandler());

    public static Response handleRequest(RequestBuilder.Request request) {
        Response response = new Response();
        response.setHttpVersion(request.getHttpVersion());
        Optional<Handler> handlerOpt = handlers
                .stream()
                .filter(h -> h.canHandle(request))
                .findFirst();
        if (handlerOpt.isPresent()) {
            handlerOpt.get().handle(request, response);
            return response;
        }

        return Response.BAD_REQUEST();
    }

}
