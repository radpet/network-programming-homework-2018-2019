package com.fmi.mpr.hw.http.request;

import java.net.Socket;

public class Request {

    private Socket client;

    public Request(Socket client) {
        this.client = client;
    }


}
