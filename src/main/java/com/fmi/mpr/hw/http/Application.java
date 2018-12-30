package com.fmi.mpr.hw.http;

import com.fmi.mpr.hw.http.common.Config;

public class Application {

    public static void main(String[] args) {
        HttpServer server = new HttpServer();
        server.serve(Config.get(Config.SERVER_HOST), Integer.parseInt(Config.get(Config.SERVER_PORT)));
    }
}