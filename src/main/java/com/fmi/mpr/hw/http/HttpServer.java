package com.fmi.mpr.hw.http;

import com.fmi.mpr.hw.http.request.RequestHandler;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    private ServerSocket socket;
    private boolean running;

    public HttpServer() {
        running = true;
    }

    public void serve(String iface, int port) {
        try {
            socket = new ServerSocket(port, 0, InetAddress.getByName(iface));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.printf("Listening on %s:%d...\n", iface, port);
        while (running) {
            try {
                handleRequest(socket.accept());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            if (socket != null)
                socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleRequest(Socket request) {
        System.out.println("Request from client with ip=" + request.getInetAddress());
        new Thread(new RequestHandler(request)).start();
    }

}