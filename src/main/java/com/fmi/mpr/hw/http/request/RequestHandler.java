package com.fmi.mpr.hw.http.request;

import com.fmi.mpr.hw.http.response.Response;
import com.fmi.mpr.hw.http.router.Router;

import java.io.IOException;
import java.net.Socket;
import java.util.Optional;

public class RequestHandler implements Runnable {

    private Socket requestSocket;

    public RequestHandler(Socket requestSocket) {
        this.requestSocket = requestSocket;
    }

    public void run() {
        try {
            requestSocket.setSoTimeout(30000);
            Response response = Response.BAD_REQUEST();
            Optional<RequestBuilder.Request> requestOpt = RequestBuilder.build(this.requestSocket.getInputStream());
            if (requestOpt.isPresent()) {
                response = Router.handleRequest(requestOpt.get());
            }
            response.commit(requestSocket.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                requestSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}
