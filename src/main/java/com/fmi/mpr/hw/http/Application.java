package com.fmi.mpr.hw.http;

public class Application {

    public static void main(String[] args) {
        String iface = "127.0.0.1";
        int port = 8080;

        HttpServer server = new HttpServer();
        server.serve(iface, port);
    }
}