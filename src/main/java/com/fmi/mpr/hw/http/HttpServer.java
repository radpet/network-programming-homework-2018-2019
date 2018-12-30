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


    private void handleClient(Socket client) {
        Thread t = new Thread(new RequestHandler(client));
        t.start();
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
                handleClient(socket.accept());
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
}